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
import model.Inventory;
import model.Part;
import model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

    /**
    * @author Marshall Christian : 5/24/2021
    * **/

public class AddProductForm implements Initializable {

    //@FXML VARIABLES
    /**
     * Product ID text field
     * **/
    public TextField addProductIdTxtFld;

    /**
     * Product Name text field
     * **/
    public TextField addProductNameTxtFld;

    /**
     * Product Inventory text field
     * **/
    public TextField addProductInvTxtFld;

    /**
     * Product Price text field
     * **/
    public TextField addProductPriceTxtFld;

    /**
     * Product Max text field
     * **/
    public TextField addProductMaxTxtFld;

    /**
     * Product Min text field
     * **/
    public TextField addProductMinTxtFld;

    /**
     * Product Part table view
     * **/
    public TableView<Part> addProductPartDataTbl;

    /**
     * Part ID table column
     * **/
    public TableColumn<Part,Integer> partDataPartId;

    /**
     * Part Name table column
     * **/
    public TableColumn<Part,String> partDataPartName;

    /**
     * Part Inventory Level table column
     * **/
    public TableColumn<Part, Integer> partDataInvLevel;

    /**
     * Part Price table column
     * **/
    public TableColumn<Part,Double> partDataPrice;

    /**
     * Associated Parts table view
     * **/
    public TableView<Part> addProductAssociatedPartTbl;

    /**
     * Associated Part ID table column
     * **/
    public TableColumn<Part,Integer> associatedPartPartId;

    /**
     * Associated Part Name table column
     * **/
    public TableColumn<Part,String> associatedPartName;

    /**
     * Associated Part Inventory Level table column
     * **/
    public TableColumn<Part,Integer> associatedPartInvLevel;

    /**
     * Associated Part Price table column
     * **/
    public TableColumn<Part,Double> associatedPartPrice;

    /**
     * Product Cancel button
     * **/
    public Button addProductCancelBtn;

    /**
     * Product Save button
     * **/
    public Button addProductSaveBtn;

    /**
     * Product Remove associated part button
     * **/
    public Button addProductRemoveAssociatedPartBtn;

    /**
     * Product Add button
     * **/
    public Button addProductAddBtn;

    /**
     * Product Search Bar text field
     * **/
    public TextField addProductSearchBar;

    /**
     * Observable List called associated parts of type Part
     * **/
    ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    
    //VARIABLES
    /**
     * Stage object called stage
     *
     * Sets the stage
     * **/
    Stage stage;

    /**
     * Parent object called scene
     *
     * Sets the scene
     * **/
    Parent scene;

    //METHODS
    /**
     * Returns the user to the main menu
     *
     * @param actionEvent return main menu called
     * **/
    public void returnToMainMenu(ActionEvent actionEvent) throws IOException {
        //THIS IS CALLED AFTER THE SAVE BUTTON IS PRESSED
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();
    }

    /**
     * Returns user to main menu when Cancel button is pressed
     *
     * @param actionEvent Cancel button action
     * **/
    public void onActionCancelMainMenu(ActionEvent actionEvent) throws IOException {
        returnToMainMenu(actionEvent);
    }

    /**
     * Saves product to product table
     *
     * If any field is empty, error is displayed
     *
     * @param actionEvent Add button action
     * **/
    public void onActionSaveAddedProduct(ActionEvent actionEvent) throws IOException {

        try {
            int id = 0;
            String name = addProductNameTxtFld.getText();
            int stock = Integer.parseInt(addProductInvTxtFld.getText());
            int min = Integer.parseInt(addProductMinTxtFld.getText());
            int max = Integer.parseInt(addProductMaxTxtFld.getText());
            double price = Double.parseDouble(addProductPriceTxtFld.getText());
            if (minIsValid(min, max) && inventoryIsValid(min, max, stock)) {
                Product product = new Product(id, name, price, stock, min, max);
                for (Part part : associatedParts) {
                    product.addAssociatedPart(part);
                }
                product.setId(Inventory.getUniqueProductId());
                Inventory.addProduct(product);
                returnToMainMenu(actionEvent);
            }
        } catch (Exception e) {
            alertDisplays(1);
        }
        //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED

        //THIS IS CALLED AFTER THE SAVE BUTTON IS PRESSED

    }

    /**
     * Removes part from the associated parts table
     *
     * @param actionEvent Remove button action
     * **/
    public void onActionRemoveAssociatedPart(ActionEvent actionEvent) {

        Part highlightedPart = addProductAssociatedPartTbl.getSelectionModel().getSelectedItem();//THIS GRABS THE HIGHLIGHTED PART

        if (highlightedPart == null) {
           alertDisplays(5);
        } else {
            //DISPLAYS THE CONFIRMATION FOR THE DELETE SCREEN
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Remove Part");
            alert.setContentText("Do you wish to remove associated part?");
            Optional<ButtonType> answer = alert.showAndWait();

            if (answer.isPresent() && answer.get() == ButtonType.OK) {
                associatedParts.remove(highlightedPart);
                addProductAssociatedPartTbl.setItems(associatedParts);
            }
        }
    }
    /**
     * Adds part to associated parts table
     *
     * @param actionEvent Add button action
     * **/
    public void onActionAddProduct(ActionEvent actionEvent) {

        Part highlightedPart = addProductPartDataTbl.getSelectionModel().getSelectedItem();//THIS GRABS THE HIGHLIGHTED PART
        if (highlightedPart == null) {
            alertDisplays(5);
        } else {
            associatedParts.add(highlightedPart);
            addProductAssociatedPartTbl.setItems(associatedParts);
        }
    }

    /**
     * Initialize method that populates parts table and associated parts table
     * **/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //POPULATING THE PARTS TABLE
        addProductPartDataTbl.setItems(Inventory.getAllParts());
        partDataPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partDataPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partDataInvLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partDataPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        //POPULATING THE PRODUCTS TABLE
        associatedPartPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInvLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    /**
     * Search bar for the part table
     *
     * User can search by Part ID and Part Name
     *
     * @param actionEvent Search Bar action
     * **/
    public void onActionAddProductSearchBar(ActionEvent actionEvent) {

        try {
            int partId = Integer.parseInt(addProductSearchBar.getText());
            addProductPartDataTbl.getSelectionModel().select(Inventory.lookupPart(partId));
        } catch (Exception e) {
            String searched = addProductSearchBar.getText();
            ObservableList<Part> part = Inventory.lookupPart(searched);
            addProductPartDataTbl.setItems(part);
        }
    }

    /**
     * Checks to make sure that the min is less than the max
     * **/
    private boolean minIsValid(int min, int max) {

        boolean valid = true;

        if (min <= 0 || min >= max) {
            valid = false;
            alertDisplays(3);
        }

        return valid;
    }
    /**
     * Checks to make sure inventory level is equal to or between min and max
     * **/
    private boolean inventoryIsValid(int min, int max, int stock) {
        boolean valid = true;

        if (stock > max || stock < min) {
            valid = false;
            alertDisplays(2);
        } return valid;

    }
    /**
     * Based on the input, displays the appropriate error message instead of crashing program
     * **/
    private void alertDisplays(int alertType) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertType) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Product");
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
