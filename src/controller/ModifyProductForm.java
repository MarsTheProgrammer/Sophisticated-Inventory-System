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

public class ModifyProductForm implements Initializable {


    //@FXML VARIABLES

    public TextField modifyProductIdTxtFld;
    public TextField modifyProductNameTxtFld;
    public TextField modifyProductInvTxtFld;
    public TextField modifyProductPriceTxtFld;
    public TextField modifyProductMaxTxtFld;
    public TextField modifyProductMinTxtFld;
    public TableView<Part> modifyProductPartDataTbl;//NEED TO MAKE SURE THIS IS PRODUCTS TABLE
    public TableColumn<Part, Integer> partDataPartId;
    public TableColumn<Part, String> partDataPartName;
    public TableColumn<Part, Integer> partDataInvLevel;
    public TableColumn<Part, Double> partDataPrice;
    public TableView<Part> modifyProductAssociatedPartTbl;
    public TableColumn<Part, Integer> associatedPartPartId;
    public TableColumn<Part, String> associatedPartName;
    public TableColumn<Part, Integer> associatedPartInvLevel;
    public TableColumn<Part, Double> associatedPartPrice;
    public Button modifyProductCancelBtn;
    public Button modifyProductSaveBtn;
    public Button modifyProductRemoveAssociatedPartBtn;
    public Button modifyProductAddBtn;
    public TextField modifyPartSearchBar;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();


    //VARIABLES

    Product highlightedProduct;
    Stage stage;
    Parent scene;

    //METHODS


    /**
     *
     **/
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

    /**
     *
     **/

    public void onActionAddProduct(ActionEvent event) {

        Part selectedPart = modifyProductPartDataTbl.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            alertDisplays(5);
        } else {
            associatedParts.add(selectedPart);
            modifyProductAssociatedPartTbl.setItems(associatedParts);
        }
    }

    /**
     *
     * **/

    public void onActionCancelMainMenu(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want cancel changes and return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToMainScreen(event);
        }
    }

    /**
     *
    **/

    public void onActionRemoveAssociatedPart(ActionEvent event) {

        Part selectedPart = modifyProductAssociatedPartTbl.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            alertDisplays(5);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to remove the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                associatedParts.remove(selectedPart);
                modifyProductAssociatedPartTbl.setItems(associatedParts);
            }
        }
    }

    /**
     *
     **/

    public void onActionSaveModifyProduct(ActionEvent event) throws IOException {

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
                    returnToMainScreen(event);
                }
        } catch (Exception e){
            alertDisplays(1);
        }
    }

    /**
     *
     **/

    public void onActionPartSearchBar(ActionEvent event) {
        try {
            int partId = Integer.parseInt(modifyPartSearchBar.getText());
            modifyProductPartDataTbl.getSelectionModel().select(Inventory.lookupPart(partId));
        } catch (Exception e) {
            String searched = modifyPartSearchBar.getText();
            ObservableList<Part> part = Inventory.lookupPart(searched);
            modifyProductPartDataTbl.setItems(part);
        }
    }

    /**
     *
     **/
    private void returnToMainScreen(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();
    }

    /**
     *
     **/
    private boolean minValid(int min, int max) {

        boolean isValid = true;

        if (min <= 0 || min >= max) {
            isValid = false;
            alertDisplays(3);
        }

        return isValid;
    }

    /**
     *
     **/
    private boolean inventoryValid(int min, int max, int stock) {

        boolean isValid = true;

        if (stock < min || stock > max) {
            isValid = false;
            alertDisplays(2);
        }

        return isValid;
    }

    /**
     *
     **/
    private void alertDisplays(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);

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
