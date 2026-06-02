package com.fastfood.controller;
import com.fastfood.model.*;
import com.fastfood.service.OrderService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;


public class OrderController {

    @FXML
    private GridPane menuItemGrid;
    @FXML
    private Label subtotalLabel;
    @FXML
    private void handleGoToCart(javafx.event.ActionEvent event) throws java.io.IOException {
        Parent cartRoot=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/fastfood/view/cartview.fxml")));
        Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(cartRoot, 800, 600));
    }

    private final List<MenuItem> Mastermenu=new ArrayList<>();

    @FXML
    public void initialize() {
        FoodItem expressBurger=new FoodItem("Express Burger", 5.99, "Classic beef patty with sauce", false);
        FoodItem chickenBurger=new FoodItem("Chicken Burger", 5.99, "Classic chicken patty with ranch", false);
        FoodItem cheeseBurger=new FoodItem("Cheese Burger", 6.49, "Classic burger with double patty and slice of American cheese", false);
        FoodItem crispyBurger=new FoodItem("Crispy Chicken Burger", 6.49, "Deep fried crispy chicken with special sauces", false);

        Mastermenu.add(expressBurger);
        Mastermenu.add(cheeseBurger);
        Mastermenu.add(chickenBurger);
        Mastermenu.add(crispyBurger);

        DrinkItem fountainSoda=new DrinkItem("Fountain Soda", 1.99, "Ice cold carbonated drink", "Medium");
        DrinkItem orangeJuice = new DrinkItem("Orange Juice", 2.49, "100% freshly squeezed orange juice", "Medium");
        DrinkItem water= new DrinkItem("Water",0.99,"Natural Cold Water","Medium");
        DrinkItem milkshake= new DrinkItem("Milkshake",4.50,"Cold Chocolate milkshake","Medium");
        DrinkItem Pepsi=new DrinkItem("Pepsi",0.99,"Pepsi Can","Medium");
        DrinkItem Sprite=new DrinkItem("Sprite",0.99,"sprite can","medium");
        Mastermenu.add(Sprite);
        Mastermenu.add(Pepsi);
        Mastermenu.add(fountainSoda);
        Mastermenu.add(orangeJuice);
        Mastermenu.add(water);
        Mastermenu.add(milkshake);

        FoodItem largeFries=new FoodItem("Large Fries", 3.49, "Golden crispy salted potato strips", false);
        FoodItem chickenNuggets=new FoodItem("10pc Chicken Nuggets", 5.49, "Crispy bite-sized white meat chicken", false);
        FoodItem chickenNuggets2=new FoodItem("20 pc Chicken Nuggets",9.99,"extra Crispy bite-sized white meat chicken",false);

        Mastermenu.add(largeFries);
        Mastermenu.add(chickenNuggets);
        Mastermenu.add(chickenNuggets2);

        DessertItem iceCreamCone=new DessertItem("Ice Cream Cone", 0.99, "Cold simple ice cream on a cone","Vanilla");
        Mastermenu.add(iceCreamCone);
        DessertItem sundaeIceCream=new DessertItem("Sundae Ice Cream", 1.99, "Cold simple ice cream served in a cup","Chocolate");
        Mastermenu.add(sundaeIceCream);
        DessertItem cheesecake=new DessertItem("Cheese Cake Slice", 2.10, "Cold Slice of cheesecake with strawberry on top","Strawberry");
        Mastermenu.add(cheesecake);
        DessertItem brownie=new DessertItem("Brownie", 1.50,"Warm brownie filled with chocolate","Chocolate");
        Mastermenu.add(brownie);
        DessertItem tiramisu=new DessertItem("Tiramisu",2.99,"coffee flavored cake","coffee");
        Mastermenu.add(tiramisu);

        MenuItem[] comboMealItems = { expressBurger, fountainSoda };
        ComboItem expressCombo = new ComboItem(
                "Express Combo Meal",
                6.99,
                "Express Burger + Medium Fountain Soda",
                comboMealItems
        );
        Mastermenu.add(expressCombo);

        MenuItem[] shareBoxItems = { cheeseBurger,cheeseBurger, largeFries,largeFries, fountainSoda, fountainSoda };
        ComboItem shareBox = new ComboItem(
                "2 Burger Share Box",
                11.99,
                "2 Burgers + 2Large Fries + 2 Medium Drinks",
                shareBoxItems
        );
        Mastermenu.add(shareBox);

        MenuItem[] familyMealItems = { expressBurger, cheeseBurger, chickenNuggets, largeFries, orangeJuice, fountainSoda };
        ComboItem familyMeal = new ComboItem(
                "Family Feast Meal",
                22.99,
                "2 Express Burger + 4 Cheese Burger + 10pc Nuggets + 4Large Fries + 4 Drinks",
                familyMealItems
        );
        Mastermenu.add(familyMeal);

        displayMenu("All");
    }

    @FXML
    private void handleCategoryChange(ActionEvent event) {

        Button clickedButton=(Button) event.getSource();
        String selectedCategory=clickedButton.getText();

        displayMenu(selectedCategory);
    }

    private void displayMenu(String category) {

        menuItemGrid.getChildren().clear();

        int column=0;
        int row=0;


        for (MenuItem item:Mastermenu) {


            VBox itemCard=new VBox(20);

            boolean matchesFilter=false;

            if (category.equals("All")) {
                matchesFilter=true;
            } else if (category.equals("Burgers")) {
                if (item instanceof FoodItem &&
                        !item.getName().contains("Fries") &&
                        !item.getName().contains("Nuggets")){
                    matchesFilter=true;
                }
            } else if (category.equals("Drinks")&&item instanceof DrinkItem) {
                matchesFilter=true;
            } else if (category.equals("Combos")&&item instanceof ComboItem) {
                matchesFilter=true;
            } else if (category.equals("Extras")) {
                if (item.getName().contains("Fries")||item.getName().contains("Nuggets")) {
                    matchesFilter=true;
                }
            } else if (category.equals("Dessert")&&item instanceof DessertItem) {
                matchesFilter=true;
            }
            if (!matchesFilter) {
                continue;
            }


            itemCard.setPadding(new Insets(10));
            itemCard.setStyle("-fx-border-color: #CCCCCC; -fx-border-radius: 5; -fx-background-color: #FFFFFF;");

            Label nameLabel=new Label(item.getName());
            nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

            Label priceLabel=new Label(String.format("$%.2f", item.getPrice()));
            priceLabel.setStyle("-fx-text-fill: #D32F2F; -fx-font-weight: bold;");

            Button addToCartBtn=new Button("Add to Cart");
            addToCartBtn.getStyleClass().add("add-to-cart-btn");
            addToCartBtn.setStyle("-fx-background-color: #126aa6; -fx-text-fill: white;");

            addToCartBtn.setOnAction(e -> {

                OrderService.getInstance().addItem(item);

                double currentSubtotal=OrderService.getInstance().calculateSubtotal();

                subtotalLabel.setText(String.format("Current Subtotal: $%.2f",currentSubtotal));
            });

            itemCard.getChildren().addAll(nameLabel, priceLabel, addToCartBtn);
            menuItemGrid.add(itemCard, column, row);

            column++;

            if (column >2) {
                column=0;
                row++;
            }
        }
    }
}