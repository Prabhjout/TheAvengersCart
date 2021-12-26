package com.example.theavengerscart;

public class GroceryBag implements Carryable {
    public static final double MAX_WEIGHT = 10;
    public static final int    MAX_ITEMS = 25;

    private GroceryItem[]   items;
    private int             numItems;
    private float           weight;

    // get methods
    public GroceryItem[] getItems() { return items; }
    public int getNumItems() { return numItems; }
    public float getWeight() { return weight; }

    // zero-parameter constructor
    public GroceryBag() {
        items = new GroceryItem[MAX_ITEMS];
        numItems = 0;
        weight = 0;
    }

    // toString method
    public String toString() {
        if (numItems > 0)
            return ("A " + weight + "kg grocery bag with " + numItems + " items");
        return "An empty grocery bag";
    }

    // 'Carryable' interface methods
    public String getContents() {
        StringBuilder contents = new StringBuilder();
        for (int i = 0; i < numItems; i++)
            contents.append("   ").append(items[i].toString()).append("\n");
        return contents.toString();
    }

    public String getDescription() {
        return ("GROCERY BAG (" + weight + "kg)");
    }

    public float getPrice() {
        float totalPrice = 0;
        for (int i = 0; i < numItems; i++ )
            totalPrice += items[i].getPrice();
        return totalPrice;
    }

    // method that adds a grocery item to the bag
    public void addItem(GroceryItem item) {
        if (((weight + item.getWeight()) <= MAX_WEIGHT) && (numItems < MAX_ITEMS)) {
            items[numItems++] = item;
            weight += item.getWeight();
        }
    }

    // method that removes a grocery item from the bag
    public void removeItem(GroceryItem item) {
        for (int i = 0; i < numItems; i++) {
            if (items[i] == item) {
                items[i] = items[numItems-1];            // rearrange contents of the bag
                items[numItems-1] = null;                // to avoid "empty spaces"
                numItems--;
                weight -= item.getWeight();
                return;                                  // remove only one instance of the item
            }
        }
    }

    // method that returns the heaviest item in the bag
    public GroceryItem heaviestItem() {
        GroceryItem heaviest = null;
        float maxWeight = 0;

        for (int i = 0; i < numItems; i++) {             // loop does not execute if bag is empty
            if (items[i].getWeight() > maxWeight) {
                maxWeight = items[i].getWeight();
                heaviest = items[i];
            }
        }
        return heaviest;
    }

    // method that returns true if the given item is in the bag
    public boolean has(GroceryItem item) {
        for (int i = 0; i < numItems; i++) {
            if (items[i] == item)
                return true;
        }
        return false;
    }
}
