package exceptions;

public class EmployeeEmptyListException extends Exception {
    public EmployeeEmptyListException() throws Exception {
        super("No employee has been registered yet.");
    }
}
