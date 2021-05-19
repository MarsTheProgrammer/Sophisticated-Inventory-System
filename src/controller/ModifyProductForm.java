package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class ModifyProductForm implements Initializable {

    //VARIABLES

    Stage stage;
    Parent scene;

    //@FXML VARIABLES

    public TextField modifyProductIdTxtFld;
    public TextField modifyProductNameTxtFld;
    public TextField modifyProductInvTxtFld;
    public TextField modifyProductPriceTxtFld;
    public TextField modifyProductMaxTxtFld;
    public TextField modifyProductMinTxtFld;
    public TableView<Part> modifyProductPartDataTbl;//NEED TO MAKE SURE THIS IS PRODUCTS TABLE
    public TableColumn<Part, Integer> partDataPartId;
    public TableColumn<Part, String> partDataPartName;
    public TableColumn<Part, Integer> partDataInvLevel;
    public TableColumn<Part, Double> partDataPrice;
    public TableView<Part> modifyProductAssociatedPartTbl;
    public TableColumn<Part, Integer> associatedPartPartId;
    public TableColumn<Part, String> associatedPartName;
    public TableColumn<Part, Integer> associatedPartInvLevel;
    public TableColumn<Part, Double> associatedPartPrice;
    public Button modifyProductCancelBtn;
    public Button modifyProductSaveBtn;
    public Button modifyProductRemoveAssociatedPartBtn;
    public Button modifyProductAddBtn;
    public TextField modifyProductSearchBar;
    ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    //METHODS

    public void onActionCancelMainMenu(ActionEvent actionEvent) throws IOException {
        //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //POPULATING THE PARTS TABLE
        modifyProductPartDataTbl.setItems(Inventory.getAllParts());
        partDataPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partDataPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partDataInvLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partDataPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        //POPULATING THE PRODUCTS TABLE
        modifyProductAssociatedPartTbl.setItems(Inventory.getAllParts());
        associatedPartPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInvLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void onActionSaveModifyProduct(ActionEvent actionEvent) {

    }

    public void onActionRemoveAssociatedPart(ActionEvent actionEvent) {
        Part highlightedPart = modifyProductAssociatedPartTbl.getSelectionModel().getSelectedItem();//THIS GRABS THE HIGHLIGHTED PART
        associatedParts.remove(highlightedPart);

    }

    public void onActionAddProduct(ActionEvent actionEvent) {

        Part highlightedPart = modifyProductPartDataTbl.getSelectionModel().getSelectedItem();//THIS GRABS THE HIGHLIGHTED PART
        associatedParts.add(highlightedPart);
    }
}
