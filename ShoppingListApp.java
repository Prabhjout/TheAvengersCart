package com.example.theavengerscart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ShoppingListApp extends Application {
    private Shopper	            model;          // model to which this view is attached
    private ShoppingListView    view;           // view that shows the state of the model

    public void start(Stage primaryStage) {
        model = new Shopper();                  // create the model
        view = new ShoppingListView(model);     // create the view

        // plug in event handlers
        view.getProductList().setOnMousePressed(event -> handleProductListSelection());
        view.getCartList().setOnMousePressed(event -> handleCartListSelection());
        view.getBuyButton().setOnAction(event -> handleBuyButtonPress());
        view.getReturnButton().setOnAction(event -> handleReturnButtonPress());
        view.getCheckoutButton().setOnAction(event -> {
            if (!view.buttonState)
                handleRestartButtonPress();
            else
                handleCheckoutButtonPress();
        });

        primaryStage.setTitle("Avengers11 Grocery Store");                 // set title of window
        primaryStage.setResizable(true);                                   // window is non-resizable
        primaryStage.setScene(new Scene(view, 840, 390, Color.web("000000")));     // set window size
        primaryStage.show();
    }

    // productList selection event handler
    private void handleProductListSelection() { view.update(); }

    // cartList selection event handler
    private void handleCartListSelection() { view.update(); }

    // buyButton pressed event handler
    private void handleBuyButtonPress() {
        int selectedIndex = view.getProductList().getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            model.addItem(view.PRODUCTS[selectedIndex]);
            view.getProductList().getSelectionModel().select(-1);
            view.update();
        }
    }

    // returnButton pressed event handler
    private void handleReturnButtonPress() {
        int selectedIndex = view.getCartList().getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            model.removeItem(model.getCart()[selectedIndex]);
            view.getCartList().getSelectionModel().select(-1);
            view.update();
        }
    }

    // checkoutButton pressed event handler
    private void handleCheckoutButtonPress() {
        checkoutPrintReceipt();
        model.packBags();
        view.buttonState = false;
        view.update();
    }

    // restartButton pressed event handler
    private void handleRestartButtonPress() {
        view.restart();
        view.buttonState = true;
        view.update();
    }

    // print receipt method
    private void checkoutPrintReceipt() {
        for (int i = 0; i < model.getNumItems(); i++)
            System.out.printf("%-31s%10.2f%n", model.getCart()[i].getDescription(), model.getCart()[i].getPrice());
        System.out.println("-----------------------------------------");
        System.out.printf("%-31s%10.2f\n%n", "TOTAL", model.computeTotalCost());
    }

    public static void main(String[] args) {
        launch(args);
    }
}