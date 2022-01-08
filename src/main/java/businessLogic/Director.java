package businessLogic;

public class Director extends Manager {

    private String department;
    private final double additionalSalary;

    public Director(String id, String name, double grossSalary) {
        super(id, name, grossSalary);
        this.department = department;
        additionalSalary = 5000;
    }
}