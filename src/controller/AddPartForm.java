package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPartForm {


    //VARIABLES
    Stage stage;
    Parent scene;

    //@FXML VARIABLES
    public ToggleGroup radioBtnTgl;
    public RadioButton addPartInHouseRadBtn;
    public RadioButton addPartOutsourcedRadBtn;
    public Label addPartChangeLabel;
    public TextField addPartIdTxtFld;
    public TextField addPartNameTxtFld;
    public TextField addPartInvTxtFld;
    public TextField addPartPriceTxtFld;
    public TextField addPartMaxTxtFld;
    public TextField addPartChangeTxtFld;
    public TextField addPartMinTxtFld;
    public Button addPartSaveBtn;
    public Button addPartCancelBtn;

    //METHODS

    public void onActionCancelMainMenu(ActionEvent actionEvent) throws IOException {
        //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();
    }

    public void onActionInHouseChange(ActionEvent actionEvent) {
        //WHEN SWITCHING TO IN-HOUSE RADIO BUTTON, THIS MAKES THE MACHINE ID DISPLAY
        addPartChangeLabel.setText("Machine ID");
    }

    public void onActionOutsourceChange(ActionEvent actionEvent) {
        //WHEN SWITCHING TO OUTSOURCED RADIO BUTTON, THIS MAKES THE COMPANY NAME DISPLAY
        addPartChangeLabel.setText("Company Name");
    }

    public void clearTxtFld(MouseEvent mouseEvent) {
        //WHEN CHANGING FROM IN-HOUSE OR OUTSOURCED, THIS CLEARS THE TEXT FIELD OF THE SHARED BOX
        //THIS IS IN HERE BECAUSE THE COMPANY NAME SHOULDN'T BE A NUMBER
        addPartChangeTxtFld.setText("");
    }
}
