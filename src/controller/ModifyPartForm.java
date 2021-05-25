package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartForm implements Initializable {

    //VARIABLES
    private Part highlightedPart;
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

    public void onActionCancelMainMenu(ActionEvent actionEvent) throws IOException {
        //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();
    }

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

    /**
     * Validates that min is greater than 0 and less than max.
     *
     * @param min The minimum value for the part.
     * @param max The maximum value for the part.
     * @return Boolean indicating if min is valid.
     */
    private boolean minValid(int min, int max) {

        boolean isValid = true;

        if (min <= 0 || min >= max) {
            isValid = false;
            alertDisplays(3);
        }

        return isValid;
    }

    /**
     * Validates that inventory level is equal too or between min and max.
     *
     * @param min The minimum value for the part.
     * @param max The maximum value for the part.
     * @param stock The inventory level for the part.
     * @return Boolean indicating if inventory is valid.
     */
    private boolean inventoryValid(int min, int max, int stock) {

        boolean isValid = true;

        if (stock < min || stock > max) {
            isValid = false;
            alertDisplays(2);
        }

        return isValid;
    }

    /**
     * Displays various alert messages.
     *
     * @param alertType Alert message selector.
     */
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
