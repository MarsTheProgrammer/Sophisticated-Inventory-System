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
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        InHouse bike1 = new InHouse(1, "Bike", 10.99, 5, 2, 3, 1 );
        Outsourced bike2 = new Outsourced(2, "Mountain Bike", 30.20, 3, 1, 2, "Biking Co.");
        Product product1 = new Product(3, "Wheel", 10.90, 5, 1, 5);

        Inventory.addPart(bike1);
        Inventory.addPart(bike2);
        Inventory.addProduct(product1);


        launch(args);
    }
}
