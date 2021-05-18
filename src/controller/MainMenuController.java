package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    //VARIABLES
    Stage stage;
    Parent scene;

    //@FXML VARIABLES
    public TextField partsSearchBar;
    public TextField productsSearchBar;
    public TableView<Part> partsTbl;
    public TableColumn<Part, Integer> partsTblPartId;
    public TableColumn<Part, String> partsTblPartName;
    public TableColumn<Part, Integer> partsTblInvLevel;
    public TableColumn<Part, Double> partsTblPrice;
    public TableView<Product> productsTbl;
    public TableColumn<Product, Integer> productsTblProductId;
    public TableColumn<Product, String> productsTblProductName;
    public TableColumn<Product, Integer> productsTblInvLevel;
    public TableColumn<Product, Double> productsTblPrice;
    public Button partsAddBtn;
    public Button partsModifyBtn;
    public Button partsDeleteBtn;
    public Button productsAddBtn;
    public Button productsModifyBtn;
    public Button productsDeleteBtn;
    public Button exitBtn;

    //METHODS

    public void onActionExit(ActionEvent actionEvent) {
        //HANDLES THE CLOSE OF THE APPLICATION
        System.exit(0);
    }

    public void onActionProductsAdd(ActionEvent actionEvent) throws IOException {
        //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addProductForm.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();
    }

    public void onActionAddPart(ActionEvent actionEvent) throws IOException {
        //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addPartForm.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();
    }

    public void onActionModifyPart(ActionEvent actionEvent) throws IOException {
        //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/modifyPartForm.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();
    }

    public void onActionModifyProduct(ActionEvent actionEvent) throws IOException {
        //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/modifyProductForm.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();
    }

    public void onActionDeletePart(ActionEvent actionEvent) {

    }

    public void onActionDeleteProduct(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //POPULATING THE PARTS TABLE
        partsTbl.setItems(Inventory.getAllParts());
        partsTblPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsTblPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsTblInvLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsTblPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        //POPULATING THE PRODUCTS TABLE
        productsTbl.setItems(Inventory.getAllProducts());
        productsTblProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productsTblProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsTblInvLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsTblPrice.setCellValueFactory(new PropertyValueFactory<>("price"));





    }
}

