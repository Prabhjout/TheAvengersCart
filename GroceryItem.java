package com.example.theavengerscart;

public class GroceryItem implements Carryable {
    protected String  name;
    protected float   price;
    protected float   weight;

    // get methods
    public String getName() { return name; }
    public float getWeight() { return weight; }

    // zero-parameter constructor
    public GroceryItem() {
        name = "?";
        price = 0;
        weight = 0;
    }

    // three-parameter constructor
    public GroceryItem(String itemName, float itemPrice, float itemWeight) {
        name = itemName;
        price = itemPrice;
        weight = itemWeight;
    }

    // toString method
    public String toString() {
        return (name + " weighing " + weight + "kg with price Rs" + String.format("%1.2f", price));
    }

    // 'Carryable' interface methods
    public String getContents() { return ""; }
    public String getDescription() { return name; }
    public float getPrice() { return price; }
}
