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

/**
 * @author Marshall Christian
 * **/

public class Main extends Application {


    /** Main start method
     *
     *      FUTURE ENHANCEMENT: As a future enhancement, we could control the duplication much better. I feel that any part can be duplicated with everything but ID. This could get confusing when you
     * begin working on a massive scale. Another enhancement could be pictures of certain parts to help get a visual.
     *
     * LOGICAL CHALLENGES: The first issue I ran into was trying to get the table views to populate. The second issue was getting the associated parts table to work. Adding from the parts table to the associated was a challenge.
     * The last and biggest issue I had, was adding associated part retention to the products.
     *
     * @param primaryStage Sets the main stage for the application. **/
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /** Main method.
     *
     * Java Docs is located in the JavaDocs folder which is inside the MarchallChristianInventorySystem file. **/
    public static void main(String[] args) {

        //ADDING TEMP PRODUCTS
        Product giantBike = new Product(1, "Giant Bike", 10.99, 15, 2, 30);
        Product scottBike = new Product(2, "Scott Bike", 30.20, 15, 1, 20);
        Product gtBike = new Product(3, "GT Bike", 30.20, 15, 1, 20);

        Inventory.addProduct(giantBike);
        Inventory.addProduct(scottBike);
        Inventory.addProduct(gtBike);

        //ADDING TEMP PARTS
        InHouse brakes = new InHouse(1, "Brakes", 12.99, 15, 1, 50, 1);
        InHouse saddle = new InHouse(4, "Saddle", 9.99, 5, 1, 50, 1);
        Outsourced tire = new Outsourced(2, "Tire", 14.99, 15, 1, 50, "Biking Co.");

        Inventory.addPart(brakes);
        Inventory.addPart(saddle);
        Inventory.addPart(tire);

        launch(args);
    }
}
