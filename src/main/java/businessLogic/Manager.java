package businessLogic;

import exceptions.EmployeeException;

import java.util.Objects;

import static facade.Facade.truncateValue;

public class Manager extends Employee {

    private String degree;

    public Manager(String id, String name, double grossSalary, String degree) throws Exception {
        super(id, name, grossSalary);
        if (degree.isEmpty() && !degree.equals("BSc") && !degree.equals("MSc") && degree.equals("PhD")) {
            throw new EmployeeException("Manager's degree must be specified as BSc, MSc or PhD.");
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

    public double calculateNetSalary() {
        return this.calculateGrossSalary() - this.calculateGrossSalary() * 0.1;
    }
}
