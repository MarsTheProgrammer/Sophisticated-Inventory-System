package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;

/**
 * @author Marshall Christian : 5/24/2021
 * **/

public class AddPartForm {

    //@FXML VARIABLES

    /** Radio buttons toggle group. **/
    public ToggleGroup radioBtnTgl;

    /** In-House radio button. **/
    public RadioButton addPartInHouseRadBtn;

    /** Outsourced radio button. **/
    public RadioButton addPartOutsourcedRadBtn;

    /** Company Name and Machine ID label. **/
    public Label addPartChangeLabel;

    /** Part ID text field. **/
    public TextField addPartIdTxtFld;

    /** Part Name text field. **/
    public TextField addPartNameTxtFld;

    /** Part Inventory Level text field. **/
    public TextField addPartInvTxtFld;

    /** Part price text field. **/
    public TextField addPartPriceTxtFld;

    /** Part max text field. **/
    public TextField addPartMaxTxtFld;

    /** Company Name and Machine ID text field. **/
    public TextField addPartChangeTxtFld;

    /** Part Min text field. **/
    public TextField addPartMinTxtFld;

    /** Part Save button. **/
    public Button addPartSaveBtn;

    /** Part Cancel button. **/
    public Button addPartCancelBtn;



    //VARIABLES

    /** Stage variable of type Stage.
     *
     * Sets the stage for the scene. **/
    Stage stage;

    /** Scene variables of type Parent.
     *
     * Sets the scene for the user. **/
    Parent scene;

    //METHODS

    /** Returns the user to the main menu screen. **/
    public void returnToMainMenu(ActionEvent actionEvent) throws IOException {
        //THIS IS CALLED AFTER THE SAVE BUTTON IS PRESSED
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();
    }

    /** Returns user to the main menu.
     *
     * When cancel button is called, the user is returned to the main menu.
     *
     * @param actionEvent Cancel button is called. **/
    public void onActionCancelMainMenu(ActionEvent actionEvent) throws IOException {
        returnToMainMenu(actionEvent);
    }

    /** Changes label to display proper text when In-House radio button is selected. **/
    public void onActionInHouseChange(ActionEvent actionEvent) {
        //WHEN SWITCHING TO IN-HOUSE RADIO BUTTON, THIS MAKES THE MACHINE ID DISPLAY
        addPartChangeLabel.setText("Machine ID");
    }

    /** Changes label to display proper text when Outsourced radio button is selected. **/
    public void onActionOutsourceChange(ActionEvent actionEvent) {
        //WHEN SWITCHING TO OUTSOURCED RADIO BUTTON, THIS MAKES THE COMPANY NAME DISPLAY
        addPartChangeLabel.setText("Company Name");
    }


    /** Saves part when save button is called.
     *
     * Saves the part only if there are no errors displayed by incorrect value types or empty fields. Also creates the part under the correct category based on radio button pressed. Returns user to main menu when part is saved successfully.
     *
     * RUNTIME ERROR: If not input was added or incorrect values were added to text fields, the application would crash. FIX: Displayed appropriate error message in substitution of crashes.
     *
     * @param actionEvent Save button called. **/
    public void onActionSaveAddedPart(ActionEvent actionEvent) throws IOException {

        try {
            int id = 0;
            String name = addPartNameTxtFld.getText();
            double price = Double.parseDouble(addPartPriceTxtFld.getText());
            int stock = Integer.parseInt(addPartInvTxtFld.getText());
            int min = Integer.parseInt(addPartMinTxtFld.getText());
            int max = Integer.parseInt(addPartMaxTxtFld.getText());
            boolean partAdded = false;

            if (minIsValid(min, max) && inventoryIsValid(min, max, stock)) {

            if (addPartInHouseRadBtn.isSelected()) {
                try {
                    int machineId = Integer.parseInt(addPartChangeTxtFld.getText());
                    InHouse inHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                    inHousePart.setId(Inventory.getUniquePartId());
                    Inventory.addPart(inHousePart);
                    partAdded = true;
                } catch (Exception e) {
                    alertDisplays(4);
                }
            }
            if(addPartOutsourcedRadBtn.isSelected()) {
                String companyName = addPartChangeTxtFld.getText();
                Outsourced outsourcedPart = new Outsourced(id, name, price, stock, min, max, companyName);
                outsourcedPart.setId(Inventory.getUniquePartId());
                Inventory.addPart(outsourcedPart);
                partAdded = true;
            }

            }
            if (partAdded) {
                returnToMainMenu(actionEvent);
            }
        } catch (Exception e) {
            alertDisplays(1);
        }
    }

    /** Tells user that min is greater than 0 and less than max.
     *
     * @param min Minimum value for part.
     * @param max Maximum value for part.
     * @return Boolean telling user if min is valid. **/
    private boolean minIsValid(int min, int max) {

        boolean valid = true;

        if (min <= 0 || min >= max) {
            valid = false;
            alertDisplays(3);
        }
        return valid;
    }

    /** Tells user that inventory level is equal or between min and max.
     *
     * @param min Minimum value for part.
     * @param max Maximum value for part.
     * @param stock Inventory level for the part.
     * @return Boolean telling user if inventory is valid. **/
    private boolean inventoryIsValid(int min, int max, int stock) {
        boolean valid = true;

        if (stock > max || stock < min) {
            valid = false;
            alertDisplays(2);
        } return valid;
    }

    /** Displays various error messages.
     *
     * Based on the input, displays the appropriate error message instead of crashing program.
     *
     * @param alertType Alert message selector. **/
    private void alertDisplays(int alertType) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertType) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Part");
                alert.setContentText("Form contains empty fields or invalid values");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Value for Inventory");
                alert.setContentText("Inventory must be a number that is equal or in between min and max values");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Value for Min");
                alert.setContentText("Min must be a number that is greater than 0 and less than Max");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Value for MachineID");
                alert.setContentText("MachineID must be numbers only.");
                alert.showAndWait();
                break;
        }
    }
}//END CLASS
