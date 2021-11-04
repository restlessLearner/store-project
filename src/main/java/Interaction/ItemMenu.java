package Interaction;

import facade.Facade;

public class ItemMenu {

    public static void itemMenu() {

        final String ITEM_MENU = "Item options menu: " + Launcher.EOL +
                "0. Return to Main Menu." + Launcher.EOL +
                "1. Create an Item." + Launcher.EOL +
                "2. Remove an Item." + Launcher.EOL +
                "3. Print all registered Items." + Launcher.EOL +
                "4. Buy an Item." + Launcher.EOL +
                "5. Update an item’s name." + Launcher.EOL +
                "6. Update an item’s price." + Launcher.EOL +
                "7. Print a specific item." + Launcher.EOL;

        int userAnswer;

        do {
            InputOutput.printInfo(ITEM_MENU);
            userAnswer = InputOutput.readInt("Input option Number: ");

            switch (userAnswer) {

                case 0:
                    Launcher.mainMenu();
                    break;
                case 1: // create item
                    String itemID = InputOutput.readString("Type item id: ");
                    String itemName = InputOutput.readString("Type item name: ");
                    double unitPrice = InputOutput.readDouble("Type price per unit: ");
                    String result1 = Launcher.facade.createItem(itemID, itemName, unitPrice);
                    break;
                case 2: // remove item
                    String idToRemove = InputOutput.readString("Type id of item to remove: ");
                    String result2 = Launcher.facade.removeItem(idToRemove);
                    break;
                case 3: // print all
                    InputOutput.printInfo(Launcher.facade.printAllItems());
                    break;
                case 4: //buy an item
                    String idToBuy = InputOutput.readString("Type an id of item to buy: ");
                    int amount = InputOutput.readInt("Type the amount: ");
                    double resultBuy = Launcher.facade.buyItem(idToBuy, amount);
                    System.out.println(resultBuy);
                    Launcher.facade.removeItem(idToBuy);
                    break;
                case 5: // update name
                    String idToChangeName = InputOutput.readString("Type id of an item to update name:");
                    String nameToUpdate = InputOutput.readString("Type a name of an item to update:");
                    String newName = InputOutput.readString("Type new name of an item:");
                    String updateNameResult = Launcher.facade.updateItemName(idToChangeName, newName);
                    break;
                case 6: // update price
                    String idToChangePrice = InputOutput.readString("Type id of an item to update price:");
                    double newPrice = InputOutput.readDouble("Type new price of an item:");
                    String updatePriceResult = Launcher.facade.updateItemPrice(idToChangePrice, newPrice);
                    break;
                case 7: // print a specific item
                    String idToPrint = InputOutput.readString("Type id of an item to print: ");
                    String itemPrintResult = Launcher.facade.printItem(idToPrint);
                    break;
                default:
                    InputOutput.printInfo("Invalid menu option. Please type another option: ");
                    itemMenu();
            }
        } while (userAnswer != 0);
    }
}


