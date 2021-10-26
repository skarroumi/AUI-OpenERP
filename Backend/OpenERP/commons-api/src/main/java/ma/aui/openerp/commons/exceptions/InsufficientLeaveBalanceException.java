package ma.aui.openerp.commons.exceptions;

public class InsufficientLeaveBalanceException extends Exception{
    public InsufficientLeaveBalanceException(String error) {
        super(error);
    }
}
