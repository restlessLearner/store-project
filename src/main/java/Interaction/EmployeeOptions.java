package Interaction;

public class EmployeeOptions {

    public static void employeeOptions() {

        final String EMPLOYEES_MENU = "Employee options menu:" + Launcher.EOL +
                "0. Return to Main Menu." + Launcher.EOL +
                "1. Create an employee (Regular Employee)." + Launcher.EOL +
                "2. Create an employee (Manager)." + Launcher.EOL +
                "3. Create an employee (Director)." + Launcher.EOL +
                "4. Create an employee (Intern)." + Launcher.EOL +
                "5. Remove an employee." + Launcher.EOL +
                "6. Print specific employee." + Launcher.EOL +
                "7. Print all registered employees." + Launcher.EOL;

        int userAnswer;

        do {
            InputOutput.printInfo(EMPLOYEES_MENU);
            userAnswer = InputOutput.readInt("Input option Number: ");

            switch (userAnswer) {
                case 0:
                    Launcher.mainMenu();
                    break;
                case 1:
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
                    InputOutput.printInfo("Invalid menu option. Please type another option: ");
                    employeeOptions();
            }
        } while (userAnswer != 0);
    }
}
