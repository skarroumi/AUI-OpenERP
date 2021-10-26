package ma.aui.openerp.commons.util;


import jodd.datetime.JDateTime;
import org.springframework.stereotype.Component;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OpenERPHelper {
    public String generateRegistrationNumber(int length) {
        // Constants
        final String NUMBERS = "1234567890";

        // Variables
        int reply=0;
        Random random = new Random();
        char[] registrationNumber = new char[length];

        for(int i = 0; i< length ; i++)
            registrationNumber[i] = NUMBERS.charAt(random.nextInt(NUMBERS.length()));

        return new String(registrationNumber);

    }

    public String getStackTrace(Throwable exp){
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        exp.printStackTrace(printWriter);
        return result.toString();
    }

    public String replace (String value,String regex,String newValue) {
        String reply=value;

        if (value.matches(regex)) {
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(value);
            reply=matcher.replaceAll(newValue);
        }

        return reply;
    }

    public boolean isNationalHoliday(String date_ddMMyyyy) {
        // Variables
        boolean result = false;
        String nationalHoliday = "";
        final String nationalHolidays[] = {"0101[0-9]{4}", "1101[0-9]{4}", "0105[0-9]{4}", "3007[0-9]{4}", "1408[0-9]{4}", "2008[0-9]{4}", "2108[0-9]{4}", "0611[0-9]{4}",
                "1811[0-9]{4}"};

        for (int i = 0; i < nationalHolidays.length; ++i) {
            nationalHoliday = nationalHolidays[i];
            if (date_ddMMyyyy.matches(nationalHoliday)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean isWorkingDay(String date) throws Exception {

        /* date Paremeter : date in format dd/MM/yyyy*/

        // Variables
        String year = "";
        String month = "";
        String day = "";
        String dte2 = "";
        String dte3 = "";
        int dayOrderNumber = 0;
        boolean workingDayFlag = false;
        boolean isNatHoliday = false;

        // Split Date value
        day = replace(date, "([0-9]{2})/([0-9]{2})/([0-9]{4})", "$1");
        month = replace(date, "([0-9]{2})/([0-9]{2})/([0-9]{4})", "$2");
        year = replace(date, "([0-9]{2})/([0-9]{2})/([0-9]{4})", "$3");

        // Compute day order number (1=Monday,...7=Sunday)
        dayOrderNumber = getDayOfweek(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

        // It's a national Holiday
        dte2 = replace(date, "([0-9]{2})/([0-9]{2})/([0-9]{4})", "$1$2$3");
        isNatHoliday = isNationalHoliday(dte2);

        // Formatage of the input date in the format yyyy-MM-dd
        dte3 = replace(date, "([0-9]{2})/([0-9]{2})/([0-9]{4})", "$3-$2-$1");

        // Control
        if ((dayOrderNumber == 6) || (dayOrderNumber == 7) || (isNatHoliday) ) {
            workingDayFlag = false;
        } else {
            workingDayFlag = true;
        }
        return workingDayFlag;
    }

    public int getDayOfweek(int year,int month,int day)
    {
        int n;
        JDateTime jdt = new JDateTime(year,month,day);
        n=jdt.getDayOfWeek();
        return n;
    }

    /* Verification of validity of leave interval. stratDate should be anterior to endDate
     * @param String startDate Start date of leave in format yyyy-MM-dd
     * @param String endDate End date of leave in format yyyy-MM-dd
     * @return boolean validity control result
     */
    public boolean isValidLeaveDateInterval(String startDate,String endDate) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        Date date1 = dateFormat.parse(startDate);
        Date date2 = dateFormat.parse(endDate);

        if ((date1.before(date2)) || (startDate.equals(endDate)))
            return true;
        else
            return false;

    }


    /* Compute leave period in days. Saturday, sunday and holidays days aren't accounting
     * @param String startDate Start date of leave in format dd/MM/yyyy
     * @param String endDate End date of leave in format dd/MM/yyyy
     * @return int leave period in days
     */

    public int leavePeriod(String startDate, String endDate) throws Exception {

        int daysCount=0;
        String day="";
        String dte_ddMMyyyy="";

        if ((startDate.equals(endDate)) && (isWorkingDay(startDate)))
            daysCount=1;
        else {
            day=startDate;

            while (!day.equals(endDate)) {
                if (isWorkingDay(day))
                    ++daysCount;

                // Next day
                dte_ddMMyyyy=getNextDate(Integer.parseInt(replace(day, "([0-9]{2})/([0-9]{2})/([0-9]{4})", "$3")), Integer.parseInt(replace(day, "([0-9]{2})/([0-9]{2})/([0-9]{4})", "$2")), Integer.parseInt(replace(day, "([0-9]{2})/([0-9]{2})/([0-9]{4})", "$1")), 1);
                day=replace(dte_ddMMyyyy, "([0-9]{2})([0-9]{2})([0-9]{4})", "$1/$2/$3");
            }

            if (isWorkingDay(day))
                ++daysCount;

        }
        return daysCount;
    }


    public boolean isValidDate(String date) {

        final String PATTERN_DATE="^([0-9]{4})-([0-9]{2})-([0-9]{2})$";

        if (date.matches(PATTERN_DATE)) {
            int year=Integer.parseInt(replace(date,PATTERN_DATE,"$1"));
            int month=Integer.parseInt(replace(date,PATTERN_DATE,"$2"));
            int day=Integer.parseInt(replace(date,PATTERN_DATE,"$3"));

            Calendar c = Calendar.getInstance();
            c.setLenient(false);
            c.set(year,(month -1),day);

            try{
                c.getTime();
            }
            catch(IllegalArgumentException iAE){
                return false;
            }
            return true;

        }else
            return false;

    }

    public String getNextDate(int year,int month,int day,int date_plus)
    {
        String s="";
        JDateTime jdt = new JDateTime(year,month,day);
        jdt.addDay(date_plus);
        s=padRight(""+jdt.getDay(),2) + padRight(""+jdt.getMonth(),2) + padRight(""+jdt.getYear(),4);

        return s;
    }

    public String padRight(String chaine,int l) {
        String c="0";
        if (chaine.length() < l) {
            int t=0;
            t=l - chaine.length();
            for (int j=0;j<t;++j)
                chaine=c + chaine;

        }

        return chaine;

    }
}
