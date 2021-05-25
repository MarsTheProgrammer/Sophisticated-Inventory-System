package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Product;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        //ADDING TEMP PRODUCTS
        Product giantBike = new Product(1, "Giant Bike", 10.99, 15, 2, 3);
        Product scottBike = new Product(2, "Scott Bike", 30.20, 15, 1, 2);
        Product gtBike = new Product(3, "GT Bike", 30.20, 15, 1, 2);

        Inventory.addProduct(giantBike);
        Inventory.addProduct(scottBike);
        Inventory.addProduct(gtBike);

        //ADDING TEMP PARTS
        InHouse brakes = new InHouse(1, "Brakes", 12.99, 15, 1, 5, 1);
        InHouse saddle = new InHouse(4, "Saddle", 9.99, 5, 1, 5, 1);
        Outsourced tire = new Outsourced(2, "Tire", 14.99, 15, 1, 5, "Biking Co.");

        Inventory.addPart(brakes);
        Inventory.addPart(saddle);
        Inventory.addPart(tire);

        launch(args);
    }
}
