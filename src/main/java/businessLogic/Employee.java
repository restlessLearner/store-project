package businessLogic;

public class Employee {

    final private String id;
    private String name;
    private double grossSalary;

    public Employee(String id, String name, double grossSalary) {
        this.id = id;
        this.name = name;
        this.grossSalary = grossSalary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }
     public double calculateNetSalary(){
        double netSalary = this.grossSalary - this.grossSalary * 0.1;
        return netSalary;
     }

     @Override
    public String toString(){
        return  getName() + "'s gross salary is " + getGrossSalary() + " SEK per month.";
     }

}
