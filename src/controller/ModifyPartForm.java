package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyPartForm {

    //VARIABLES
    Stage stage;
    Parent scene;

    public RadioButton modifyPartInHouseRadBtn;
    public ToggleGroup radioBtnTgl;
    public RadioButton modifyPartOutsourcedRadBtn;
    public Label modifyPartChangeLabel;
    public TextField modifyPartIdTxtFld;
    public TextField modifyPartNameTxtFld;
    public TextField modifyPartInvTxtFld;
    public TextField modifyPartPriceTxtFld;
    public TextField modifyPartMaxTxtFld;
    public TextField modifyPartChangeTxtFld;
    public TextField modifyPartMinTxtFld;
    public Button modifyPartSaveBtn;
    public Button modifyPartCancelBtn;

    public void onActionInHouseChange(ActionEvent actionEvent) {
        //WHEN SWITCHING TO IN-HOUSE RADIO BUTTON, THIS MAKES THE MACHINE ID DISPLAY
        modifyPartChangeLabel.setText("MachineID");
    }

    public void onActionOutsourceChange(ActionEvent actionEvent) {
        //WHEN SWITCHING TO OUTSOURCED RADIO BUTTON, THIS MAKES THE COMPANY NAME DISPLAY
        modifyPartChangeLabel.setText("Company Name");
    }

    public void clearTxtFld(MouseEvent mouseEvent) {
        //WHEN CHANGING FROM IN-HOUSE OR OUTSOURCED, THIS CLEARS THE TEXT FIELD OF THE SHARED BOX
        //THIS IS IN HERE BECAUSE THE COMPANY NAME SHOULDN'T BE A NUMBER
        modifyPartChangeTxtFld.setText("");
    }

    public void onActionCancelMainMenu(ActionEvent actionEvent) throws IOException {
        //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();
    }
}
