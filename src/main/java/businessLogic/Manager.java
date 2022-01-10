package businessLogic;

import exceptions.EmployeeInformationException;

import static facade.Facade.truncateValue;

public class Manager extends Employee {

    private String degree;

    public Manager(String id, String name, double grossSalary, String degree) throws Exception {
        super(id, name, grossSalary);
        if (degree.isEmpty() && !degree.equals("BSc") && !degree.equals("MSc") && degree.equals("PhD")) {
            throw new EmployeeInformationException("Degree must be one of the options: PhD, MSc or PhD.");
        }
        this.degree = degree;
    }

    public String getDegree() {
        return this.degree;
    }

    public void setDegree(String newDegree) {
        this.degree = newDegree;
    }

    @Override
    public String toString() {
        return getDegree() + super.getName() + "'s gross salary is " + String.format("%.2f", truncateValue(this.getGrossSalary(), 2) + " SEK per month.");
    }


    public double calculateGrossSalary() {
        double netSalary = 0.0;
        if (degree.equals("BSc")) {
            netSalary = super.getGrossSalary() + super.getGrossSalary() * 0.1;
        } else if (degree.equals("MSc")) {
            netSalary = super.getGrossSalary() + super.getGrossSalary() * 0.2;
        } else if (degree.equals("PhD")) {
            netSalary = super.getGrossSalary() + super.getGrossSalary() * 0.35;
        }
        return netSalary;
    }

    @Override
    public double calculateNetSalary() {
        return this.calculateGrossSalary() - this.calculateGrossSalary() * 0.1;
    }
}
