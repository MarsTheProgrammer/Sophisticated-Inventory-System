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

public class ModifyProductForm {

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
    public TableView<?> modifyProductPartDataTbl;
    public TableColumn<?, ?> partDataPartId;
    public TableColumn<?, ?> partDataPartName;
    public TableColumn<?, ?> partDataInvLevel;
    public TableColumn<?, ?> partDataPrice;
    public TableView<?> modifyProductAssociatedPartTbl;
    public TableColumn<?, ?> associatedPartPartId;
    public TableColumn<?, ?> associatedPartNumber;
    public TableColumn<?, ?> associatedPartInvLevel;
    public TableColumn<?, ?> associatedPartPrice;
    public Button modifyProductCancelBtn;
    public Button modifyProductSaveBtn;
    public Button modifyProductRemoveAssociatedPartBtn;
    public Button modifyProductAddBtn;
    public TextField modifyProductSearchBar;

    //METHODS

    public void onActionCancelMainMenu(ActionEvent actionEvent) throws IOException {
        //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();

    }
}
