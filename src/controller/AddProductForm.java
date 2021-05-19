package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;
import java.io.IOException;

public class AddProductForm {

    //@FXML VARIABLES
    public TextField addProductIdTxtFld;
    public TextField addProductNameTxtFld;
    public TextField addProductInvTxtFld;
    public TextField addProductPriceTxtFld;
    public TextField addProductMaxTxtFld;
    public TextField addProductMinTxtFld;
    public TableView<Product> addProductPartDataTbl;
    public TableColumn<Product,Integer> partDataPartId;
    public TableColumn<Product,String> partDataPartName;
    public TableColumn<Product, Integer> partDataInvLevel;
    public TableColumn<Product,Double> partDataPrice;
    public TableView<Part> addProductAssociatedPartTbl;
    public TableColumn<Part,Integer> associatedPartPartId;
    public TableColumn<Part,String> associatedPartNumber;
    public TableColumn<Part,Integer> associatedPartInvLevel;
    public TableColumn<Part,Double> associatedPartPrice;
    public Button addProductCancelBtn;
    public Button addProductSaveBtn;
    public Button addProductRemoveAssociatedPartBtn;
    public Button addProductAddBtn;
    public TextField addProductSearchBar;


    //VARIABLES
    Stage stage;
    Parent scene;


    //METHODS

    public void returnToMainMenu(ActionEvent actionEvent) throws IOException {
        //THIS IS CALLED AFTER THE SAVE BUTTON IS PRESSED
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();
    }

    public void onActionCancelMainMenu(ActionEvent actionEvent) throws IOException {
        returnToMainMenu(actionEvent);
    }

    public void onActionSaveAddedProduct(ActionEvent actionEvent) throws IOException {

        int id = Integer.parseInt(addProductIdTxtFld.getText());//CONVERTS THE STRING TO THE INT DATA TYPE
        String name = addProductNameTxtFld.getText();
        int stock = Integer.parseInt(addProductInvTxtFld.getText());
        int min = Integer.parseInt(addProductMinTxtFld.getText());
        int max = Integer.parseInt(addProductMaxTxtFld.getText());
        double price = Double.parseDouble(addProductPriceTxtFld.getText());

        Inventory.addProduct(new Product(id, name, price, stock, min, max));
        System.out.println("You saved the Product");

        //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED
        returnToMainMenu(actionEvent);
        //THIS IS CALLED AFTER THE SAVE BUTTON IS PRESSED

    }
}
