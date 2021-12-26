package com.example.theavengerscart;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class ShoppingListView extends Pane implements Initializable {
    public boolean buttonState = true;         // reflects 'true' for "checkout"

    public final GroceryItem[] PRODUCTS = {
            new GroceryItem("Ice-cream", 200f, 0.5f),
            new GroceryItem("Aata", 330f, 10f),
            new GroceryItem("Chocolate",10f,0.052f),
            new GroceryItem("Biscuit", 25f, 0.120f),
            new GroceryItem("chips", 10f, 0.213f),
            new GroceryItem("Shampoo",2f,0.02f),
            new GroceryItem("Soap", 23f, 0.320f),
            new GroceryItem("Coca-Cola", 20f, 0.500f),
            new GroceryItem("Amul Butter",45f,0.420f),
            new GroceryItem("Sanitizer", 50f, 0.100f),
            new GroceryItem("Facewash", 40f, 0.100f),
            new GroceryItem("Milk",56f,1f),
            new GroceryItem("Eggs",7f,1f),
            new GroceryItem("Yogurt",50f,0.400f)
    };

    // model to which this view is attached
    private Shopper	                model;

    // user interface components needed by the controller
    private ListView<GroceryItem>   productList, contentsList;
    private ListView<String>        cartList;
    private Button                  buyButton, returnButton, checkoutButton;
    private TextField               priceField;

    // public methods to allow access to components
    public ListView<GroceryItem> getProductList() { return productList; }
    public ListView<String> getCartList() { return cartList; }
    public Button getBuyButton() { return buyButton; }
    public Button getReturnButton() { return returnButton; }
    public Button getCheckoutButton() { return checkoutButton; }

    public ShoppingListView(Shopper m) {
        // store the model for access later
        model = m;

        // create font
        Font font;
        font = new Font("Arial Rounded MT Bold", 14.0);

        // add the labels
        Label label = new Label("PRODUCTS");
        label.relocate(10, 12);
        label.setPrefSize(300, 30);
        label.setFont(font);
        label.setAlignment(Pos.CENTER);
        //label.setTextFill(Color.web("#FE9927"));
        label.setBackground(new Background(new BackgroundFill(Color.rgb(254, 99, 27), new CornerRadii(1.0), new Insets(0.0))));
        getChildren().add(label);

        label = new Label("SHOPPING CART");
        label.relocate(320, 12);
        label.setPrefSize(200, 30);
        label.setFont(font);
        label.setAlignment(Pos.CENTER);
        label.setBackground(new Background(new BackgroundFill(Color.rgb(254, 99, 27), new CornerRadii(1.0), new Insets(0.0))));
        getChildren().add(label);

        label = new Label("CONTENTS");
        label.relocate(530, 12);
        label.setPrefSize(300, 30);
        label.setFont(font);
        label.setAlignment(Pos.CENTER);
        label.setBackground(new Background(new BackgroundFill(Color.rgb(254, 99, 27), new CornerRadii(1.0), new Insets(0.0))));
        getChildren().add(label);

        label = new Label("TOTAL :");
        label.relocate(665, 355);
        label.setPrefSize(60, 22);
        label.setFont(font);
        label.setAlignment(Pos.CENTER);
        label.setBackground(new Background(new BackgroundFill(Color.rgb(214, 34, 92), new CornerRadii(1.0), new Insets(0.0))));
        getChildren().add(label);

        // add the listviews
        productList = new ListView<>();
        productList.relocate(10, 44);
        productList.setPrefSize(300, 300);
        productList.setStyle("-fx-background-color:#00ff00");

        cartList = new ListView<>();
        cartList.relocate(320, 44);
        cartList.setPrefSize(200, 300);
        cartList.setStyle("-fx-background-color:#00ff00");

        contentsList = new ListView<>();
        contentsList.relocate(530, 44);
        contentsList.setPrefSize(300, 300);
        contentsList.setStyle("-fx-background-color:#00ff00");

        // add the buttons
        buyButton = new Button("Buy");
        buyButton.relocate(10, 355);
        buyButton.setPrefSize(300, 25);
        Font font1=Font.font("San serif" +
                "FontWeight.BOLD",14);
        buyButton.setFont(font1);
        //buyButton.setStyle("-fx-background-color:#1111ff"); buyButton.setStyle("-fx-text-fill:#009234");

        returnButton = new Button("Return");
        returnButton.relocate(320, 355);
        returnButton.setPrefSize(200, 25);
        returnButton.setFont(font);
        returnButton.setStyle("-fx-background-color:#ffffff");
        returnButton.setStyle("-fx-text-fill:#009234");

        checkoutButton = new Button("Checkout");
        checkoutButton.relocate(530, 355);
        checkoutButton.setPrefSize(120, 25);
        checkoutButton.setFont(font);
        //checkoutButton.setStyle("-fx-background-color:#0011ff");
        //checkoutButton.setStyle("-fx-text-fill:#009234");

        // add the textfield
        priceField = new TextField();
        priceField.relocate(730, 355);
        priceField.setPrefSize(100, 25);
        priceField.setFont(font);
        priceField.setAlignment(Pos.BASELINE_CENTER);
        priceField.setEditable(false);

        // add remaining components to the window
        getChildren().addAll(productList, cartList, contentsList, buyButton, returnButton, checkoutButton, priceField);

        // call update() to ensure model contents are shown
        update();
    }

    public void update() {
        // populate productList with products
        int selectedIndex = productList.getSelectionModel().getSelectedIndex();
        productList.setItems(FXCollections.observableArrayList(PRODUCTS));
        productList.getSelectionModel().select(selectedIndex);

        // enable/disable the buyButton accordingly
        buyButton.setDisable(selectedIndex < 0);

        // populate cartList with cart items
        String[] exactList = new String[model.getNumItems()];
        for (int i = 0; i < model.getNumItems(); i++)
            exactList[i] = model.getCart()[i].getDescription();
        selectedIndex = cartList.getSelectionModel().getSelectedIndex();
        cartList.setItems(FXCollections.observableArrayList(exactList));
        cartList.getSelectionModel().select(selectedIndex);

        // enable/disable the returnButton accordingly
        returnButton.setDisable(selectedIndex < 0);

        // populate contentsList with bag items
        if (selectedIndex >= 0) {
            if (model.getCart()[selectedIndex] instanceof GroceryBag) {
                GroceryBag bag = (GroceryBag) model.getCart()[selectedIndex];
                GroceryItem[] items = new GroceryItem[bag.getNumItems()];
                for (int i = 0; i < bag.getNumItems(); i++)
                    items[i] = bag.getItems()[i];
                contentsList.setItems(FXCollections.observableArrayList(items));
            }
            else {
                contentsList.setItems(null);
            }
        }

        // update priceField to display total cost
        priceField.setText(String.format("Rs%1.2f", model.computeTotalCost()));

        // enable/disable the checkoutButton accordingly
        // and handle checkout / restart shopping views
        if (!buttonState) {
            buyButton.setDisable(true);
            returnButton.setDisable(true);
            productList.setDisable(true);
            checkoutButton.setDisable(false);
            checkoutButton.setText("Restart Shopping");
        }
        else {
            productList.setDisable(false);
            checkoutButton.setDisable(model.getNumItems() == 0);
            checkoutButton.setText("Checkout");
        }
    }

    public void restart() {
        for (int i = 0; i < model.getCart().length; i++) {
            model.removeItem(model.getCart()[i]);
        }

        cartList.setItems(null);
        contentsList.setItems(null);
        priceField.setText(String.format("Rs%1.2f", model.computeTotalCost()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
