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


    public void onActionSaveAddedPart(ActionEvent actionEvent) throws IOException {

        try {
            int id = 0;
            String name = addPartNameTxtFld.getText();
            double price = Double.parseDouble(addPartPriceTxtFld.getText());
            int stock = Integer.parseInt(addPartInvTxtFld.getText());
            int min = Integer.parseInt(addPartMinTxtFld.getText());
            int max = Integer.parseInt(addPartMaxTxtFld.getText());
            boolean partWasAdded = false;

            if (minIsValid(min, max) && inventoryIsValid(min, max, stock)) {

            if (addPartInHouseRadBtn.isSelected()) {
                try {
                    int machineId = Integer.parseInt(addPartChangeTxtFld.getText());
                    InHouse inHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                    inHousePart.setId(Inventory.getUniquePartId());
                    Inventory.addPart(inHousePart);
                    partWasAdded = true;
                } catch (Exception e) {
                    alertDisplays(4);
                }
            }
            if(addPartOutsourcedRadBtn.isSelected()) {
                String companyName = addPartChangeTxtFld.getText();
                Outsourced outsourcedPart = new Outsourced(id, name, price, stock, min, max, companyName);
                outsourcedPart.setId(Inventory.getUniquePartId());
                Inventory.addPart(outsourcedPart);
                partWasAdded = true;
            }
            }
            if (partWasAdded) {
                returnToMainMenu(actionEvent);
            }
        } catch (Exception e) {
            alertDisplays(1);
        }

    }

    private boolean minIsValid(int min, int max) {

        boolean valid = true;

        if (min <= 0 || min >= max) {
            valid = false;
            alertDisplays(3);
        }

        return valid;
    }

    private boolean inventoryIsValid(int min, int max, int stock) {
        boolean valid = true;

        if (stock > max || stock < min) {
            valid = false;
            alertDisplays(2);
        } return valid;

    }

    private void alertDisplays(int alertType) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);

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
        }


    }



}//END CLASS
