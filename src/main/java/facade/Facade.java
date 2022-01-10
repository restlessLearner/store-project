package facade;

import businessLogic.Employee;
import businessLogic.Transaction;
import input.UserInput;
import menu.Launcher;
import businessLogic.Item;
import businessLogic.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Facade {

    static final String EOL = System.lineSeparator();

    private ArrayList<Item> items;
    private ArrayList<Transaction> transactions;
    public ArrayList<Employee> employees;


    // This class only has the skeleton of the methods used by the test.
    // You must fill in this class with your own code. You can (and should) create more classes
    // that implement the functionalities listed in the Facade and in the Test Cases.

    public Facade() {
        this.items = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    //////////////////////////////// methods that were added to the initial Facade class//////////////////////////

    public static double truncateValue(double value, int decimal) {
        double truncated = ((int) (value * Math.pow(10, decimal)) / Math.pow(10, decimal));
        return truncated;
    }

    public Item retrieveItem(String itemID) {
        Item neededItem = null;
        for (Item currentItem : items) {
            if (currentItem.getItemId().equals(itemID)) {
                return currentItem;
            }
        }
        return neededItem;
    }

    public int getAllReviews() {
        int allReviewsAmount = 0;
        for (Item currentItem : items) {
            allReviewsAmount += getNumberOfReviews(currentItem.getItemId());
        }
        return allReviewsAmount;
    }

    public boolean containsTransaction(String itemID) {
        boolean hasTransaction = false;
        for (Transaction transaction : transactions) {
            if (transaction.getItemID().equals(itemID)) {
                hasTransaction = true;
            }
        }
        return hasTransaction;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public String createItem(String itemID, String itemName, double unitPrice) {
        if (itemID.isEmpty() || itemName.isEmpty() || unitPrice <= 0) {
            return "Invalid data for item.";
        } else {
            if (containsItem(itemID)) {
                return "An item with ID " + itemID + " is occupied. Try ID that differs from " + itemID;
            } else {
                items.add(new Item(itemID, itemName, unitPrice));
                return "Item " + itemID + " was registered successfully.";
            }
        }
    }

    public String printItem(String itemID) {
        if (items.contains(retrieveItem(itemID))) {
            return retrieveItem(itemID).toString();
        }
        return "Item " + itemID + " was not registered yet.";
    }

    public String removeItem(String itemID) {
        if (containsItem(itemID)) {
            items.remove(retrieveItem(itemID));
            return "Item " + itemID + " was successfully removed.";
        } else {
            return "Item " + itemID + " could not be removed.";
        }
    }

    public boolean containsItem(String itemID) {
        if (items.contains(retrieveItem(itemID))) {
            return true;
        }
        return false;
    }

    public double buyItem(String itemID, int amount) {
        int DISCOUNT_THRESHOLD = 4;
        double totalValue = -1.0;
        if (items.contains(retrieveItem(itemID)) && amount > 0) {
            if (amount > DISCOUNT_THRESHOLD) {
                totalValue = truncateValue(((amount - DISCOUNT_THRESHOLD) * retrieveItem(itemID).getUnitPrice() * 0.7
                        + retrieveItem(itemID).getUnitPrice() * DISCOUNT_THRESHOLD), 2);
                transactions.add(new Transaction(itemID, amount, totalValue));
            } else if (amount < 4) {
                totalValue = truncateValue((retrieveItem(itemID).getUnitPrice() * amount), 2);
                transactions.add(new Transaction(itemID, amount, totalValue));
            }
        }
        return totalValue;
    }

    public String reviewItem(String itemID, String reviewComment, int reviewGrade) {
        if (!items.contains(retrieveItem(itemID))) {
            return "Item " + itemID + " was not registered yet.";
        } else {
            if (reviewGrade < 1 || reviewGrade > 5) {
                return "Grade values must be between 1 and 5.";
            } else {
                retrieveItem(itemID).addReview(new Review(reviewGrade, reviewComment));
                return "Your item review was registered successfully.";
            }
        }
    }

    public String reviewItem(String itemID, int reviewGrade) {
        if (!items.contains(retrieveItem(itemID))) {
            return "Item " + itemID + " was not registered yet.";
        } else {
            if (reviewGrade < 1 || reviewGrade > 5) {
                return "Grade values must be between 1 and 5.";
            } else {
                retrieveItem(itemID).addReview(new Review(reviewGrade));
                return "Your item review was registered successfully.";
            }
        }
    }

    public String getItemCommentsPrinted(String itemID) {
        return getItemComments(itemID).toString();
    }

    public List<String> getItemComments(String itemID) {
        ArrayList<String> comments = new ArrayList<>();
        if (items.contains(retrieveItem(itemID))) {
            for (int i = 0; i < retrieveItem(itemID).numOfReviews(); i++) {
                String comment = retrieveItem(itemID).getReview(i).getReviewComment();
                comments.add(comment);
            }
        }
        return comments;
    }

    public double getItemMeanGrade(String itemID) {
        int sum = 0;
        for (int i = 0; i < retrieveItem(itemID).numOfReviews(); i++) {
            sum += retrieveItem(itemID).getReview(i).getReviewGrade();
        }
        return truncateValue((double) sum / retrieveItem(itemID).numOfReviews(), 1);
    }

    public int getNumberOfReviews(String itemID) {
        if (containsItem(itemID)) {
            return retrieveItem(itemID).numOfReviews();
        }
        return 0;
    }

    public String getPrintedItemReview(String itemID, int reviewNumber) {
        String operationResult = "";
        int correctIndex = reviewNumber - 1;
        Item itemToPrintReview = retrieveItem(itemID);
        if (!items.contains(itemToPrintReview)) {
            operationResult = "Item " + itemID + " was not registered yet.";
        } else {
            if (itemToPrintReview.getReviews().isEmpty()) {
                operationResult = "Item " + itemToPrintReview.getItemName() + " has not been reviewed yet.";
            } else {
                if (reviewNumber >= 1 && reviewNumber <= getNumberOfReviews(itemID)) {
                    operationResult = itemToPrintReview.getReviews().get(correctIndex).toString();

                } else {
                    operationResult = "Invalid review number. Choose between 1 and " + getNumberOfReviews(itemID) + ".";
                }
            }
        }
        return operationResult;
    }

    public String getPrintedReviews(String itemID) {
        String printOutput = "";
        Item item = retrieveItem(itemID);
        if (!items.contains(item)) {
            printOutput = "Item " + itemID + " was not registered yet.";
        } else if (items.contains(item) && item.getReviews().isEmpty()) {
            printOutput = "Review(s) for " + item.toString() + EOL +
                    "The item " + item.getItemName() + " has not been reviewed yet.";
        } else {
            String listOfReviews = "";
            for (int i = 0; i < item.getReviews().size(); i++) {
                listOfReviews += item.getReviews().get(i) + EOL;
            }
            printOutput = "Review(s) for " + item.toString() + EOL + listOfReviews;
        }
        return printOutput;
    }

    public String printMostReviewedItems() {
        if (items.isEmpty()) {
            return "No items registered yet.";
        } else {
            if (getAllReviews() == 0) {
                return "No items were reviewed yet.";
            } else {
                String result = "Most reviews: " + retrieveItem(getMostReviewedItems().get(0)).numOfReviews() + " review(s) each." + EOL;
                for (String currentItem : getMostReviewedItems()) {
                    result += retrieveItem(currentItem).toString() + EOL;
                }
                return result;
            }
        }
    }

    public List<String> getMostReviewedItems() {
        ArrayList<String> mostReviewed = new ArrayList<>();
        if (items.size() != 0) {
            int high = 0;
            for (Item currentItem : items) {
                if (currentItem.numOfReviews() >= high && currentItem.numOfReviews() != 0) {
                    high = currentItem.numOfReviews();
                }
            }
            for (Item currentItem : items) {
                if (currentItem.numOfReviews() == high && currentItem.numOfReviews() != 0) {
                    mostReviewed.add(currentItem.getItemId());
                }
            }
        }
        return mostReviewed;
    }

    public List<String> getLeastReviewedItems() {
        ArrayList<String> leastReviewed = new ArrayList<>();
        if (items.size() != 0) {
            int low = items.get(0).numOfReviews();
            for (Item currentItem : items) {
                if (currentItem.numOfReviews() <= low && currentItem.numOfReviews() != 0) {
                    low = currentItem.numOfReviews();
                }
            }
            for (Item currentItem : items) {
                if (currentItem.numOfReviews() == low && currentItem.numOfReviews() != 0) {
                    leastReviewed.add(currentItem.getItemId());
                }
            }
        }
        return leastReviewed;
    }

    public String printLeastReviewedItems() {
        if (items.isEmpty()) {
            return "No items registered yet.";
        } else {
            int reviewsNumber = 0;
            for (Item currentItem : items) {
                reviewsNumber += getNumberOfReviews(currentItem.getItemId());
            }
            if (reviewsNumber != 0) {
                String result = "Least reviews: " + retrieveItem(getLeastReviewedItems().get(0)).numOfReviews() + " review(s) each." + EOL;
                for (String item : getLeastReviewedItems()) {
                    result += retrieveItem(item).toString() + EOL;
                }
                return result;
            } else {
                return "No items were reviewed yet.";
            }
        }
    }

    public double getTotalProfit() {
        double totalProfit = 0.0;
        if (!transactions.isEmpty()) {
            for (Item currentItem : items) {
                totalProfit += getProfit(currentItem.getItemId());
            }
            totalProfit = truncateValue(totalProfit, 2);
        }
        return totalProfit;
    }

    public String printItemTransactions(String itemID) {
        String output;
        if (!containsItem(itemID)) {
            output = "Item " + itemID + " was not registered yet.";
        } else {
            if (!containsTransaction(itemID)) {
                output = "Transactions for item: " + retrieveItem(itemID).toString() + EOL +
                        "No transactions have been registered for item " + retrieveItem(itemID).getItemId() + " yet.";
            } else {
                output = "Transactions for item: " + retrieveItem(itemID).toString() + EOL;
                for (Transaction transaction : transactions) {
                    if (transaction.getItemID().equals(itemID)) {
                        output += transaction.toString() + EOL;
                    }
                }
            }
        }
        return output;
    }

    public int getTotalUnitsSold() {
        int totalUnitsSold = 0;
        if (!transactions.isEmpty()) {
            for (Transaction transaction : transactions) {
                totalUnitsSold += transaction.getAmount();
            }
        }
        return totalUnitsSold;
    }

    public int getTotalTransactions() {
        return transactions.size();
    }

    public double getProfit(String itemID) {
        double profit = 0.0;
        if (containsTransaction(itemID)) {
            for (Transaction transaction : transactions) {
                if (transaction.getItemID().equals(itemID)) {
                    profit += transaction.getPurchasePrice();
                }
            }
            profit = truncateValue(profit, 2);
        }
        return profit;
    }

    public int getUnitsSolds(String itemID) {
        int unitsSold = 0;
        if (containsTransaction(itemID)) {
            for (Transaction transaction : transactions) {
                if (transaction.getItemID().equals(itemID)) {
                    unitsSold += transaction.getAmount();
                }
            }
        }
        return unitsSold;
    }

    public String printAllTransactions() {
        String output;
        if (items.isEmpty()) {
            output = "All purchases made: " + EOL +
                    "Total profit: 0.00 SEK" + EOL +
                    "Total items sold: 0 units" + EOL +
                    "Total purchases made: 0 transactions" + EOL +
                    "------------------------------------" + EOL +
                    "------------------------------------" + EOL;
        } else {
            if (transactions.size() == 0) {
                output = "All purchases made: " + EOL +
                        "Total profit: 0.00 SEK" + EOL +
                        "Total items sold: 0 units" + EOL +
                        "Total purchases made: 0 transactions" + EOL +
                        "------------------------------------" + EOL +
                        "------------------------------------" + EOL;
            } else {
                output = "All purchases made: " + EOL +
                        "Total profit: " + String.format("%.2f", truncateValue(getTotalProfit(), 2)) + " SEK" + EOL +
                        "Total items sold: " + getTotalUnitsSold() + " units" + EOL +
                        "Total purchases made: " + getTotalTransactions() + " transactions" + EOL +
                        "------------------------------------" + EOL;
                for (Transaction transaction : transactions) {
                    output += transaction.toString() + EOL;
                }
                output = output + "------------------------------------" + EOL;
            }
        }
        return output;
    }

    public String printWorseReviewedItems() {
        if (items.isEmpty()) {
            return "No items registered yet.";
        } else {
            if (!getWorseReviewedItems().isEmpty()) {
                String output = "Items with worst mean reviews:" + EOL +
                        "Grade: " + getItemMeanGrade(getWorseReviewedItems().get(0)) + EOL;
                for (String item : getWorseReviewedItems()) {
                    output += retrieveItem(item).toString() + EOL;
                }
                return output;
            } else {
                return "No items were reviewed yet.";
            }
        }
    }

    public String printBestReviewedItems() {
        if (items.isEmpty()) {
            return "No items registered yet.";
        } else {
            if (!getBestReviewedItems().isEmpty()) {
                String output = "Items with best mean reviews:" + EOL +
                        "Grade: " + getItemMeanGrade(getBestReviewedItems().get(0)) + EOL;
                for (String item : getBestReviewedItems()) {
                    output += retrieveItem(item).toString() + EOL;
                }
                return output;
            } else {
                return "No items were reviewed yet.";
            }
        }
    }

    public List<String> getWorseReviewedItems() {
        ArrayList<String> worseReviewedItems = new ArrayList<>();
        double worstValue = getItemMeanGrade(items.get(0).getItemId());
        if (getAllReviews() != 0 && !items.isEmpty()) {
            for (Item currentItem : items) {
                if (getItemMeanGrade(currentItem.getItemId()) <= worstValue
                        && getItemMeanGrade(currentItem.getItemId()) != 0) {
                    worstValue = getItemMeanGrade(currentItem.getItemId());
                }
            }
            for (Item currentItem : items) {
                if (getItemMeanGrade(currentItem.getItemId()) == worstValue
                        && getItemMeanGrade(currentItem.getItemId()) != 0) {
                    worseReviewedItems.add(currentItem.getItemId());
                }
            }
        }
        return worseReviewedItems;
    }

    public List<String> getBestReviewedItems() {
        ArrayList<String> bestReviewedItems = new ArrayList<>();
        double bestValue = 0.0;
        if (getAllReviews() != 0 && !items.isEmpty()) {
            for (Item currentItem : items) {
                if (getItemMeanGrade(currentItem.getItemId()) >= bestValue
                        && getItemMeanGrade(currentItem.getItemId()) != 0) {
                    bestValue = getItemMeanGrade(currentItem.getItemId());
                }
            }
            for (Item currentItem : items) {
                if (getItemMeanGrade(currentItem.getItemId()) == bestValue
                        && getItemMeanGrade(currentItem.getItemId()) != 0) {
                    bestReviewedItems.add(currentItem.getItemId());
                }
            }
        }
        return bestReviewedItems;
    }

    public String printAllReviews() {
        String printOut = "";
        if (items.isEmpty()) {
            return "No items registered yet.";
        } else {
            for (Item currentItem : items) {
                String list = "";
                if (!currentItem.getReviews().isEmpty()) {
                    String reviewList = "";
                    for (int i = 0; i < currentItem.getReviews().size(); i++) {
                        reviewList += currentItem.getReviews().get(i) + EOL;
                    }
                    list += "Review(s) for " + currentItem.toString() + EOL +
                            reviewList + "------------------------------------" + EOL;
                }
                printOut = "All registered reviews:" + EOL +
                        "------------------------------------" + EOL +
                        list;
            }
            return printOut;
        }
    }

    public String updateItemName(String itemID, String newName) {
        String output;
        if (!items.contains(retrieveItem(itemID))) {
            output = "Item " + itemID + " was not registered yet.";
        } else {
            if (!itemID.isEmpty() && !newName.isEmpty()) {
                retrieveItem(itemID).setItemName(newName);
                output = "Item " + itemID + " was updated successfully.";
            } else {
                output = "Invalid data for item.";
            }
        }
        return output;
    }

    public String updateItemPrice(String itemID, double newPrice) {
        String output;
        if (!items.contains(retrieveItem(itemID))) {
            output = "Item " + itemID + " was not registered yet.";
        } else {
            if (!itemID.isEmpty() && newPrice > 0) {
                retrieveItem(itemID).setUnitPrice(newPrice);
                output = "Item " + itemID + " was updated successfully.";
            } else {
                output = "Invalid data for item.";
            }
        }
        return output;
    }

    public String printAllItems() {
        String output;
        if (items.isEmpty()) {
            output = "No items registered yet.";
        } else {
            output = "All registered items:" + EOL;
            for (Item currentItem : items) {
                output += currentItem.toString() + EOL;
            }
        }
        return output;
    }

    public String printMostProfitableItems() {
        String output;
        if (items.isEmpty()) {
            output = "No items registered yet.";
        } else {
            if (transactions.size() == 0) {
                output = "No items were bought yet.";
            } else {
                double highValue = 0.0;
                for (Item currentItem : items) {
                    if (getProfit(currentItem.getItemId()) >= highValue) {
                        highValue = getProfit(currentItem.getItemId());
                    }
                }
                output = "Most profitable items: " + EOL + "Total profit: " + String.format("%.2f", truncateValue(highValue, 2)) + " SEK" + EOL;
                for (Item currentItem : items) {
                    if (getProfit(currentItem.getItemId()) == highValue) {
                        output += currentItem.toString() + EOL;
                    }
                }
            }
        }
        return output;
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception {
        Employee newEmployee = new Employee(employeeID, employeeName, grossSalary);
        return "Employee " + employeeID + "was registered successfully";
    }

    public String printEmployee(String employeeID) throws Exception {
        return "";
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree) throws
            Exception {
        return "";
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws
            Exception {
        return "";
    }

    public double getNetSalary(String employeeID) throws Exception {
        return -1.0;
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String
            degree, String dept) throws Exception {
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