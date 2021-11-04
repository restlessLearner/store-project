package Interaction;

public class TransactionsMenu {

    public static void transactionsMenu() {

        final String TRANSACTIONS_MENU = "Transaction History options menu:" + Launcher.EOL +
                "0. Return to Main Menu." + Launcher.EOL +
                "1. Print total profit from all item purchases." + Launcher.EOL +
                "2. Print total units sold from all item purchases." + Launcher.EOL +
                "3. Print the total number of item transactions made." + Launcher.EOL +
                "4. Print all transactions made." + Launcher.EOL +
                "5. Print the total profit of a specific item." + Launcher.EOL +
                "6. Print the number of units sold of a specific item." + Launcher.EOL +
                "7. Print all transactions of a specific item." + Launcher.EOL +
                "8. Print item with highest profit." + Launcher.EOL;

        int userAnswer;

        do {
            InputOutput.printInfo(TRANSACTIONS_MENU);
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
                default:
                    InputOutput.printInfo("Invalid menu option. Please type another option: ");
                    transactionsMenu();
            }
        } while (userAnswer !=0);
    }
}