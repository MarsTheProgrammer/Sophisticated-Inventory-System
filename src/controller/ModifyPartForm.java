package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Marshall Christian : 5/24/2021
 * **/

public class ModifyPartForm implements Initializable {
    //FXML Variables

    /** In-House radio button **/
    public RadioButton modifyPartInHouseRadBtn;

    /** Radio button toggle group **/
    public ToggleGroup radioBtnTgl;

    /** Outsourced radio button **/
    public RadioButton modifyPartOutsourcedRadBtn;

    /** Company Name and Machine ID label **/
    public Label modifyPartChangeLabel;

    /** Part ID text field. **/
    public TextField modifyPartIdTxtFld;

    /** Part Name text field. **/
    public TextField modifyPartNameTxtFld;

    /** Part Inventory Level text field. **/
    public TextField modifyPartInvTxtFld;

    /** Part price text field. **/
    public TextField modifyPartPriceTxtFld;

    /** Part Max text field. **/
    public TextField modifyPartMaxTxtFld;

    /** Part Min text field. **/
    public TextField modifyPartChangeTxtFld;

    /** Company Name and Machine ID text field. **/
    public TextField modifyPartMinTxtFld;

    /** Part Min text field **/
    public Button modifyPartSaveBtn;

    /** Part cancel button. **/
    public Button modifyPartCancelBtn;

    //VARIABLES

    /** Part highlightedPart variable. **/
    private Part highlightedPart;

    /** Stage variable of type Stage.
     *
     * Sets the stage for the scene. **/
    Stage stage;

    /** Scene variables of type Parent.
     *
     * Sets the scene for the user. **/
    Parent scene;


    /** Changes form to In-House.
     *
     * When the in-House radio button is pressed, Company Name text field is changed to Machine ID.
     *
     * @param actionEvent Radio button called. **/
    public void onActionInHouseChange(ActionEvent actionEvent) {
        //WHEN SWITCHING TO IN-HOUSE RADIO BUTTON, THIS MAKES THE MACHINE ID DISPLAY
        modifyPartChangeLabel.setText("MachineID");
    }

    /** Changes form to Outsourced.
     *
     * When the outsourced radio button is pressed, MachineID text field is changed to Company Name.
     *
     * @param actionEvent Radio button called. **/
    public void onActionOutsourceChange(ActionEvent actionEvent) {
        //WHEN SWITCHING TO OUTSOURCED RADIO BUTTON, THIS MAKES THE COMPANY NAME DISPLAY
        modifyPartChangeLabel.setText("Company Name");
    }

    /** Returns user to the main menu when cancel button is called.
     *
     * @param actionEvent Cancel button called. **/
    public void onActionCancelMainMenu(ActionEvent actionEvent) throws IOException {
        //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();
    }

    /** Initialize method that populates parts table and products table. **/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        highlightedPart = MainMenuController.getHighlightedPart();

        if (highlightedPart instanceof InHouse) {
            modifyPartInHouseRadBtn.isSelected();
            modifyPartChangeLabel.setText("MachineID");
            modifyPartChangeTxtFld.setText(String.valueOf(((InHouse) highlightedPart).getMachineId()));
        }
        if (highlightedPart instanceof Outsourced) {
            modifyPartOutsourcedRadBtn.isSelected();
            modifyPartChangeLabel.setText("Company Name");
            modifyPartChangeTxtFld.setText(((Outsourced) highlightedPart).getCompanyName());
        }//THIS SHOULD HANDLE THE LAST TEXT BOX AND MAKE SURE IT IS FILLED

        modifyPartIdTxtFld.setText(String.valueOf(highlightedPart.getId()));
        modifyPartNameTxtFld.setText(String.valueOf(highlightedPart.getName()));
        modifyPartPriceTxtFld.setText(String.valueOf(highlightedPart.getPrice()));
        modifyPartInvTxtFld.setText(String.valueOf(highlightedPart.getStock()));
        modifyPartMinTxtFld.setText(String.valueOf(highlightedPart.getMin()));
        modifyPartMaxTxtFld.setText(String.valueOf(highlightedPart.getMax()));



    }

    /** Saves modified part.
     *
     * When save button is called, the part is saved along with if it is In-House or Outsourced. Then the user is returned to the main menu.
     *
     * RUNTIME ERROR: Empty fields or invalid values entered would crash the application. FIX: Displayed error messages to fix problem instead of crashing application.
     *
     * @param actionEvent Save button called. **/
    public void onActionSaveModifyPart(ActionEvent actionEvent) {
        try {
            int id = highlightedPart.getId();
            String name = modifyPartNameTxtFld.getText();
            double price = Double.parseDouble(modifyPartPriceTxtFld.getText());
            int stock = Integer.parseInt(modifyPartInvTxtFld.getText());
            int min = Integer.parseInt(modifyPartMinTxtFld.getText());
            int max = Integer.parseInt(modifyPartMaxTxtFld.getText());
            int machineId;
            String companyName;
            boolean partAdded = false;

            if (minValid(min, max) && inventoryValid(min, max, stock)) {

                if (modifyPartInHouseRadBtn.isSelected()) {
                    try {
                        machineId = Integer.parseInt(modifyPartChangeTxtFld.getText());
                        InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                        Inventory.addPart(newInHousePart);
                        partAdded = true;
                    } catch (Exception e) {
                        alertDisplays(4);
                    }
                }

                if (modifyPartOutsourcedRadBtn.isSelected()) {
                    companyName = modifyPartChangeTxtFld.getText();
                    Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max,
                            companyName);
                    Inventory.addPart(newOutsourcedPart);
                    partAdded = true;
                }

                if (partAdded) {
                    Inventory.deletePart(highlightedPart);
                    //THIS TAKES TO THE MAIN MENU WHEN PART IS SAVED
                    stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
                    stage.setScene(new Scene(scene));//sets up the scene
                    stage.show();
                }
            }
        } catch(Exception e) {
            alertDisplays(1);
        }
    }

    /** Tells user that min is greater than 0 and less than max.
     *
     * @param min Minimum value for part.
     * @param max Maximum value for part.
     * @return Boolean telling user if min is valid. **/
    private boolean minValid(int min, int max) {

        boolean isValid = true;

        if (min <= 0 || min >= max) {
            isValid = false;
            alertDisplays(3);
        }
        return isValid;
    }

    /** Tells user that inventory level is equal or between min and max.
     *
     * @param min Minimum value for part.
     * @param max Maximum value for part.
     * @param stock Inventory level for the part.
     * @return Boolean telling user if inventory is valid. **/
    private boolean inventoryValid(int min, int max, int stock) {

        boolean isValid = true;

        if (stock < min || stock > max) {
            isValid = false;
            alertDisplays(2);
        }
        return isValid;
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
                alert.setHeaderText("Error Modifying Part");
                alert.setContentText("Form contains empty fields or invalid values");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Value for Inventory");
                alert.setContentText("Inventory must be a number that is equal to or in between min and max values");
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
            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("Part Was Not Highlighted");
                alert.showAndWait();
                break;
        }
    }
}//END OF CLASS
