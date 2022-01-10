package menu;

import facade.Facade;
import input.UserInput;

public class EmployeeOptions {

    static final String EOL = System.lineSeparator();
    static final Facade facade = Launcher.facade;

    public static void employeeOptions() throws Exception {

        final String EMPLOYEES_MENU = "Employee options menu:" + EOL +
                "0. Return to Main Menu." + EOL +
                "1. Create an employee (Regular Employee)." + EOL +
                "2. Create an employee (Manager)." + EOL +
                "3. Create an employee (Director)." + EOL +
                "4. Create an employee (Intern)." + EOL +
                "5. Remove an employee." + EOL +
                "6. Print specific employee." + EOL +
                "7. Print all registered employees." + EOL;

        int userAnswer;

        do {
            UserInput.printInfo(EMPLOYEES_MENU);
            userAnswer = UserInput.readInt("Input option Number: ");

            switch (userAnswer) {
                case 0:
                    Launcher.mainMenu();
                    break;
                case 1: //Create an employee (Regular Employee)
                    String employeeID = UserInput.readString("Enter a new employee's ID: ");
                    String employeeName = UserInput.readString("Enter a new employee's name: ");
                    double grossSalary = UserInput.readDouble("Enter a new employee's salary before taxes: ");
                    facade.createEmployee(employeeID, employeeName, grossSalary);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                default:
                    UserInput.printInfo("Invalid menu option. Please type another option: ");
                    employeeOptions();
            }
        } while (userAnswer != 0);
    }
}
