package businessLogic;

import exceptions.EmployeeException;

public class Employee {

    final private String id;
    private String name;
    private double grossSalary;

    public Employee(String id, String name, double grossSalary)  throws Exception {
        if (id.isEmpty()){
            throw new EmployeeException("Id number cannot not be empty.");
        } else if (name.isEmpty()){
            throw new EmployeeException("Name cannot be empty.");
        } else if (grossSalary<=0){
            throw new EmployeeException("Salary must be greater than 0.");
        }
        else{
            this.id = id;
            this.name = name;
            this.grossSalary = grossSalary;
        }

    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return this.id;
    }
    public double getGrossSalary() {
        return this.grossSalary;
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
