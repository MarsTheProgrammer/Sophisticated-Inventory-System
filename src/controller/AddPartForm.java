package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

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

    public void onActionSaveAddedPart(ActionEvent actionEvent) throws IOException {

        //WE NEED TO FIGURE OUT EVENT HANDLES SUCH AS, INCORRECT DATA TYPE ADDED
        int id = Integer.parseInt(addPartIdTxtFld.getText());
        String name = addPartNameTxtFld.getText();
        double price = Double.parseDouble(addPartPriceTxtFld.getText());
        int stock = Integer.parseInt(addPartInvTxtFld.getText());
        int min = Integer.parseInt(addPartMinTxtFld.getText());
        int max = Integer.parseInt(addPartMaxTxtFld.getText());

        //THIS WILL ADD THE PART THE CORRECT SCREEN
        if (addPartInHouseRadBtn.isSelected()) {
            int machineId = Integer.parseInt(addPartChangeTxtFld.getText());
            Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));
            System.out.println("You added a part to the In-House Category");
        } else {
            String companyName = addPartChangeTxtFld.getText();
            Inventory.addPart((new Outsourced(id, name, price, stock, min, max, companyName)));
            System.out.println("You added a part to the Outsourced Category");
        }

        returnToMainMenu(actionEvent);
    }
}
