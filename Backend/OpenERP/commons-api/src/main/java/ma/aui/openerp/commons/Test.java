package ma.aui.openerp.commons;

import ma.aui.openerp.commons.util.OpenERPHelper;

public class Test {
    public static void main(String[] args) {
        OpenERPHelper openERPHelper = new OpenERPHelper();
        System.out.println(openERPHelper.generateIdentificationId(5));
    }
}
