package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Marshall Christian : 5/24/2021
 * **/

public class ModifyProductForm implements Initializable {

    //@FXML VARIABLES

    /** Product ID text field. **/
    public TextField modifyProductIdTxtFld;

    /** Product Name text field. **/
    public TextField modifyProductNameTxtFld;

    /** Product Inventory Level text field. **/
    public TextField modifyProductInvTxtFld;

    /** Product Price text field. **/
    public TextField modifyProductPriceTxtFld;

    /** Product Max text field. **/
    public TextField modifyProductMaxTxtFld;

    /** Product Min text field. **/
    public TextField modifyProductMinTxtFld;

    /** Product part table view. **/
    public TableView<Part> modifyProductPartDataTbl;

    /** Part ID table column. **/
    public TableColumn<Part, Integer> partDataPartId;

    /** Part Name table column. **/
    public TableColumn<Part, String> partDataPartName;

    /** Part Inventory Level table column. **/
    public TableColumn<Part, Integer> partDataInvLevel;

    /** Part price table column. **/
    public TableColumn<Part, Double> partDataPrice;

    /** Associated Part table view. **/
    public TableView<Part> modifyProductAssociatedPartTbl;

    /** Associated Part Part ID table view. **/
    public TableColumn<Part, Integer> associatedPartPartId;

    /** Associated Part Part Name table view. **/
    public TableColumn<Part, String> associatedPartName;

    /** Associated Part Part Inventory Level table view. **/
    public TableColumn<Part, Integer> associatedPartInvLevel;

    /** Associated Part Part Price table view. **/
    public TableColumn<Part, Double> associatedPartPrice;

    /** Product cancel button. **/
    public Button modifyProductCancelBtn;

    /** Product save button. **/
    public Button modifyProductSaveBtn;

    /** Product remove associated parts button. **/
    public Button modifyProductRemoveAssociatedPartBtn;

    /** Product add button. **/
    public Button modifyProductAddBtn;

    /** Part Search Bar text field. **/
    public TextField modifyPartSearchBar;

    /** Associated Parts observable list. **/
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    //VARIABLES

    /** Product highlightedProduct variable. **/
    Product highlightedProduct;

    /** Stage variable of type Stage.
     *
     * Sets the stage for the scene. **/
    Stage stage;

    /** Scene variables of type Parent.
     *
     * Sets the scene for the user. **/
    Parent scene;

    //METHODS

    /** Initialize method that populates parts table and products table.
     *
     * Initializes, populates parts and products tables, grabs highlighted product from the main menu, and grabs all associated parts with the product. **/
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        highlightedProduct = MainMenuController.getHighlightedProduct();
        associatedParts = highlightedProduct.getAllAssociatedParts();

        partDataPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partDataPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partDataInvLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partDataPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        modifyProductPartDataTbl.setItems(Inventory.getAllParts());

        associatedPartPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInvLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        modifyProductAssociatedPartTbl.setItems(associatedParts);

        modifyProductIdTxtFld.setText(String.valueOf(highlightedProduct.getId()));
        modifyProductNameTxtFld.setText(highlightedProduct.getName());
        modifyProductInvTxtFld.setText(String.valueOf(highlightedProduct.getStock()));
        modifyProductPriceTxtFld.setText(String.valueOf(highlightedProduct.getPrice()));
        modifyProductMaxTxtFld.setText(String.valueOf(highlightedProduct.getMax()));
        modifyProductMinTxtFld.setText(String.valueOf(highlightedProduct.getMin()));
    }

    /** Add parts to the associated parts table.
     *
     * When the user highlights a parts and presses the add button. The part is added to the associated parts table.
     *
     * @param actionEvent Add button called. **/
    public void onActionAddProduct(ActionEvent actionEvent) {

        Part highlightedPart = modifyProductPartDataTbl.getSelectionModel().getSelectedItem();

        if (highlightedPart == null) {
            alertDisplays(5);
        } else {
            associatedParts.add(highlightedPart);
            modifyProductAssociatedPartTbl.setItems(associatedParts);
        }
    }

    /** Returns user to the main menu.
     *
     * When cancel button is called, the user is returned to the main menu.
     *
     * @param actionEvent Cancel button is called. **/
    public void onActionCancelMainMenu(ActionEvent actionEvent) throws IOException { returnToMainScreen(actionEvent); }


    /** Removes the highlighted associated part.
     *
     * When delete button is called. Associated part is removed after remove prompt.
     *
     * @param actionEvent Remove button is called. **/
    public void onActionRemoveAssociatedPart(ActionEvent actionEvent) {

        Part highlightedPart = modifyProductAssociatedPartTbl.getSelectionModel().getSelectedItem();

        if (highlightedPart == null) {
            alertDisplays(5);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to remove the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                associatedParts.remove(highlightedPart);
                modifyProductAssociatedPartTbl.setItems(associatedParts);
            }
        }
    }

    /** Saves modified product and all associated parts.
     *
     * When save button is pressed, all information about product is saved along with associated parts.
     *
     * RUNTIME ERROR: Empty fields or invalid values entered would crash the application. FIX: Displayed error messages to fix problem instead of crashing application.
     *
     * @param actionEvent Save button called. **/
    public void onActionSaveModifyProduct(ActionEvent actionEvent) throws IOException {

        try {
            int id = highlightedProduct.getId();
            String name = modifyProductNameTxtFld.getText();
            double price = Double.parseDouble(modifyProductPriceTxtFld.getText());
            int stock = Integer.parseInt(modifyProductInvTxtFld.getText());
            int min = Integer.parseInt(modifyProductMinTxtFld.getText());
            int max = Integer.parseInt(modifyProductMaxTxtFld.getText());

                if (minValid(min, max) && inventoryValid(min, max, stock)) {

                    Product newProduct = new Product(id, name, price, stock, min, max);

                    for (Part part : associatedParts) {
                        newProduct.addAssociatedPart(part);
                    }
                    Inventory.addProduct(newProduct);
                    Inventory.deleteProduct(highlightedProduct);
                    returnToMainScreen(actionEvent);
                }
        } catch (Exception e){
            alertDisplays(1);
        }
    }

    /** Part search bar is called. User can search by Part ID or Name.
     *
     * @param actionEvent Search bar text field called. **/
    public void onActionPartSearchBar(ActionEvent actionEvent) {
        try {
            int partId = Integer.parseInt(modifyPartSearchBar.getText());
            modifyProductPartDataTbl.getSelectionModel().select(Inventory.lookupPart(partId));
        } catch (Exception e) {
            String searched = modifyPartSearchBar.getText();
            ObservableList<Part> part = Inventory.lookupPart(searched);
            modifyProductPartDataTbl.setItems(part);
        }
    }

    /** Returns the user to the main menu. **/
    private void returnToMainScreen(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();
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
                alert.setHeaderText("Error Modifying Product");
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
