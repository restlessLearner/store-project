package businessLogic;

public class Manager extends Employee {

    private String degree;

    public Manager(String id, String name, double grossSalary) {
        super(id, name, grossSalary);
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return getDegree() + super.getName() + "'s gross salary is " + super.getGrossSalary() + " SEK per month.";
    }

    public double calculateNetSalary(String degree) {
        double netSalary = 0.0;
        if (degree == "BSc") {
            netSalary = super.calculateNetSalary() + super.getGrossSalary() * 0.1;
        } else if (degree == "MSc") {
            netSalary = super.calculateNetSalary() + super.getGrossSalary() * 0.2;
        } else if (degree == "PhD") {
            netSalary = super.calculateNetSalary() + super.getGrossSalary() * 0.35;
        }
        return netSalary;
    }
}
