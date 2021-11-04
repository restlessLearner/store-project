package facade;

import Interaction.InputOutput;
import Interaction.ItemMenu;
import Interaction.Launcher;
import Store.Item;
import Store.Review;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Facade {

    private ArrayList<Item> itemsList;

    //private ArrayList<Review> reviewsList;
    // This class only has the skeleton of the methods used by the test.
    // You must fill in this class with your own code. You can (and should) create more classes
    // that implement the functionalities listed in the Facade and in the Test Cases.

    public Facade() {
        this.itemsList = new ArrayList<>();
        //this.reviewsList = new ArrayList<>();
    }

    public String createItem(String itemID, String itemName, double unitPrice) {
        if (itemID.isEmpty() || itemName.isEmpty() || unitPrice <= 0) {
            return InputOutput.printInfo("Invalid data for item.");
        } else {
            if (containsItem(itemID)) {
                return InputOutput.printInfo("An item with ID " + itemID + " is occupied. Try ID that differs from " + itemID);
            } else {
                Item item = new Item(itemID, itemName, unitPrice);
                itemsList.add(item);
                return InputOutput.printInfo("Item " + item.getItemId() + " was registered successfully.");
            }
        }
    }

    public String printItem(String itemID) {
        String notification = "";
        Item itemToPrint = retrieveItem(itemID);
        if (!itemsList.contains(itemToPrint)) {
            return InputOutput.printInfo("Item " + itemID + " was not registered yet.");
        } else {
            return InputOutput.printInfo(itemToPrint.toString());
        }
    }

    public String removeItem(String itemID) {
        String notification = "";
        Item itemToRemove = retrieveItem(itemID);
        if (itemsList.contains(itemToRemove)) {
            itemsList.remove(itemToRemove);
            notification = InputOutput.printInfo("Item " + itemToRemove.getItemId() + " was successfully removed.");
        } else {
            notification = InputOutput.printInfo("Item " + itemID + " could not be removed.");
        }
        return notification;
    }

    public boolean containsItem(String itemID) {
        if (itemsList.contains(retrieveItem(itemID))) {
            return true;
        }
        return false;
    }
// created method for retrieving an item

    public Item retrieveItem(String itemID) {
        Item neededItem = null;
        for (Item currentItem : itemsList) {
            if (currentItem.getItemId().equals(itemID)) {
                return currentItem;
            }
        }
        return neededItem;
    }

    public double buyItem(String itemID, int amount) {
        double totalValue;
        Item itemToBuy = retrieveItem(itemID);
        if (itemsList.contains(itemToBuy)) {
            return totalValue = itemToBuy.purchase(amount);
        } else {
            return totalValue = -1;
        }
    }

    public String reviewItem(String itemID, String reviewComment, int reviewGrade) {
        Item itemToReview = retrieveItem(itemID);
        if (!itemsList.contains(itemToReview)) {
            return InputOutput.printInfo("Item " + itemID + " was not registered yet.");
        } else {
            if (reviewGrade < 1 && reviewGrade > 5) {
                return InputOutput.printInfo("Grade values must be between 1 and 5.");
            } else {
                Review review = new Review(reviewComment, reviewGrade);
                itemToReview.reviewsList.add(review);
                return InputOutput.printInfo("Your item review was registered successfully.");
            }
        }
    }

    public String reviewItem(String itemID, int reviewGrade) {
        Item itemToReview = retrieveItem(itemID);
        if (!itemsList.contains(itemToReview)) {
            return InputOutput.printInfo("Item " + itemID + " was not registered yet.");
        }
            /*if (reviewGrade < 1 && reviewGrade > 5) {
                return InputOutput.printInfo("Grade values must be between 1 and 5.");
            } */
        else {
            Review review = new Review(null, reviewGrade);
            itemToReview.reviewsList.add(review);
            return InputOutput.printInfo("Your item review was registered successfully.");
        }
    }

    public String getItemCommentsPrinted(String itemID) {
        return "";
    }

    public List<String> getItemComments(String itemID) {
        return null;
    }

    public double getItemMeanGrade(String itemID) {
        return -1.0;
    }

    public int getNumberOfReviews(String itemID) {
        return -1;
    }

    public String getPrintedItemReview(String itemID, int reviewNumber) {
        return "";
    }

    public String getPrintedReviews(String itemID) {
        return "";
    }

    public String printMostReviewedItems() {
        return "";
    }

    public List<String> getMostReviewedItems() {
        return null;
    }

    public List<String> getLeastReviewedItems() {
        return null;
    }

    public String printLeastReviewedItems() {
        return "";
    }

    public double getTotalProfit() {
        return -1.0;
    }

    public String printItemTransactions(String itemID) {
        return "";
    }

    public int getTotalUnitsSold() {
        return -1;
    }

    public int getTotalTransactions() {
        return -1;
    }

    public double getProfit(String itemID) {
        return -1.0;
    }

    public int getUnitsSolds(String itemID) {
        return -1;
    }

    public String printAllTransactions() {
        return "";
    }

    public String printWorseReviewedItems() {
        return "";
    }

    public String printBestReviewedItems() {
        return "";
    }

    public List<String> getWorseReviewedItems() {
        return null;
    }

    public List<String> getBestReviewedItems() {
        return null;
    }

    public String printAllReviews() {
        return "";
    }

    public String updateItemName(String itemID, String newName) {
        Item itemToUpdateName = retrieveItem(itemID);
        if (itemsList.contains(itemToUpdateName)) {
            if (!itemID.isEmpty() && !newName.isEmpty()) {
                itemToUpdateName.setItemName(newName);
                return InputOutput.printInfo("Item " + itemID + " was updated successfully.");
            } else {
                return InputOutput.printInfo("Invalid data for item.");
            }
        } else {
            return InputOutput.printInfo("Item " + itemID + " was not registered yet.");
        }
    }

    public String updateItemPrice(String itemID, double newPrice) {
        Item itemToUpdatePrice = retrieveItem(itemID);
        if (itemsList.contains(itemToUpdatePrice)) {
            if (!itemID.isEmpty() && newPrice > 0) {
                itemToUpdatePrice.setUnitPrice(newPrice);
                return InputOutput.printInfo("Item " + itemID + " was updated successfully.");
            } else {
                return InputOutput.printInfo("Invalid data for item.");
            }
        } else {
            return InputOutput.printInfo("Item " + itemID + " was not registered yet.");
        }
    }

    public String printAllItems() {
        String printList = "";
        if (!itemsList.isEmpty()) {
            Iterator<Item> itr = itemsList.iterator();
            for (Item item : itemsList) {
                printList += item.toString() + Launcher.EOL;
            }
            return "All registered items:" + Launcher.EOL + printList;
        } else {
            return "No items registered yet.";
        }
    }

    public String printMostProfitableItems() {
        return "";
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception {
        return "";
    }

    public String printEmployee(String employeeID) throws Exception {
        return "";
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree) throws Exception {
        return "";
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception {
        return "";
    }

    public double getNetSalary(String employeeID) throws Exception {
        return -1.0;
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String dept) throws Exception {
        return "";
    }

    public String removeEmployee(String empID) throws Exception {
        return "";
    }

    public String printAllEmployees() throws Exception {
        return "";
    }

    public double getTotalNetSalary() throws Exception {
        return -1.0;
    }

    public String printSortedEmployees() throws Exception {
        return "";
    }

    public String updateEmployeeName(String empID, String newName) throws Exception {
        return "";
    }

    public String updateInternGPA(String empID, int newGPA) throws Exception {
        return "";
    }

    public String updateManagerDegree(String empID, String newDegree) throws Exception {
        return "";
    }

    public String updateDirectorDept(String empID, String newDepartment) throws Exception {
        return "";
    }

    public String updateGrossSalary(String empID, double newSalary) throws Exception {
        return "";
    }

    public Map<String, Integer> mapEachDegree() throws Exception {
        return null;
    }

    public String promoteToManager(String empID, String degree) throws Exception {
        return "";

    }

    public String promoteToDirector(String empID, String degree, String department) throws Exception {
        return "";
    }

    public String promoteToIntern(String empID, int gpa) throws Exception {
        return "";
    }
}



