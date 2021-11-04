package Store;

import java.util.ArrayList;

// create class for item. Item is responsible for ID, name, price (overall price as well),
// list of reviews so these holding here

public class Item {
    final private String itemID;
    private String itemName;
    double unitPrice;
    public ArrayList<Review> reviewsList;

// constructor for Item

    public Item(String itemID, String itemName, double unitPrice) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.reviewsList = new ArrayList<>();
    }

// setters

    public String getItemName() {
        return itemName;
    }

    public String getItemId() {
        return itemID;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

// setters

    public void setItemName(String itemName) {
            this.itemName = itemName;
    }

    public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
    }

    // Overriding toString method

    public String toString() {
        String itemInfo = getItemId() + ": " + getItemName() + ". " + truncateString(getUnitPrice()) + " SEK";
        return itemInfo;
    }

    public double purchase(int amount) {
        final int DISCOUNT_THRESHOLD = 4;
        double purchasePrice = 0.0;
        if (amount > DISCOUNT_THRESHOLD) {
            purchasePrice = truncateDouble((amount - DISCOUNT_THRESHOLD) * getUnitPrice() * 0.7 + getUnitPrice() * DISCOUNT_THRESHOLD);
        } else {
            purchasePrice = truncateDouble(getUnitPrice() * amount);
        }
        return purchasePrice;
    }

// method for truncating decimals for printing items

    public String truncateString(double value) {
        int truncated = (int) (value * Math.pow(10, 2));
        double newValue = (double) truncated / Math.pow(10, 2);
        String truncationResult = String.format("%.2f", newValue).replace(",", ".");
        return truncationResult;
    }

    public double truncateDouble(double value) {
        int truncated = (int) (value * Math.pow(10, 2));
        double newValue = (double) truncated / Math.pow(10, 2);
        return newValue;
    }
}


// looking for item comment if exists

    /*public ArrayList<String> getItemComments() {
        ArrayList<String> newList = new ArrayList<>();
        for (int i = 0; i < reviewsList.size(); i++) {
            Review currentReview = reviewsList.get(i);
            String itemHasReview = currentReview.getReviewComment();
            if (currentReview.reviewComment.isEmpty()) {
                return
            } else {
            }
            return newList;
        }
    }*/

 /*public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Item)
            return true;
        if ((obj.getClass() == Item.class))
            return true;
        return false;*/
