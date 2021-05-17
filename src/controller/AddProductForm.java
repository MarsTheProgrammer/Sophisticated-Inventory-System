package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddProductForm {

    //@FXML VARIABLES
    public TextField addProductIdTxtFld;
    public TextField addProductNameTxtFld;
    public TextField addProductInvTxtFld;
    public TextField addProductPriceTxtFld;
    public TextField addProductMaxTxtFld;
    public TextField addProductMinTxtFld;
    public TableView<?> addProductPartDataTbl;
    public TableColumn<?,?> partDataPartId;
    public TableColumn<?,?> partDataPartName;
    public TableColumn<?,?> partDataInvLevel;
    public TableColumn<?,?> partDataPrice;
    public TableView<?> addProductAssociatedPartTbl;
    public TableColumn<?,?> associatedPartPartId;
    public TableColumn<?,?> associatedPartNumber;
    public TableColumn<?,?> associatedPartInvLevel;
    public TableColumn<?,?> associatedPartPrice;
    public Button addProductCancelBtn;
    public Button addProductSaveBtn;
    public Button addProductRemoveAssociatedPartBtn;
    public Button addProductAddBtn;
    public TextField addProductSearchBar;

    //VARIABLES
    Stage stage;
    Parent scene;


    //METHODS
    public void onActionCancelMainMenu(ActionEvent actionEvent) throws IOException {
        //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();
    }
}
