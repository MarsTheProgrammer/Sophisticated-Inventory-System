package controller;

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

public class MainMenuController implements Initializable {

    //@FXML VARIABLES

    /** Parts search bar text field. **/
    public TextField partsSearchBar;

    /** Products search bar text field. **/
    public TextField productsSearchBar;

    /** Part table view **/
    public TableView<Part> partsTbl;

    /** Parts ID table column. **/
    public TableColumn<Part, Integer> partsTblPartId;

    /** Part Name table column. **/
    public TableColumn<Part, String> partsTblPartName;

    /** Part Inventory Level table column. **/
    public TableColumn<Part, Integer> partsTblInvLevel;

    /** Part Price table column. **/
    public TableColumn<Part, Double> partsTblPrice;

    /** Products table view. **/
    public TableView<Product> productsTbl;

    /** Products ID table column. **/
    public TableColumn<Product, Integer> productsTblProductId;

    /** Products Name table column. **/
    public TableColumn<Product, String> productsTblProductName;

    /** Products Inventory Level table column. **/
    public TableColumn<Product, Integer> productsTblInvLevel;

    /** Products Price table column. **/
    public TableColumn<Product, Double> productsTblPrice;

    /** Parts add button. **/
    public Button partsAddBtn;

    /** Parts modify button. **/
    public Button partsModifyBtn;

    /** Parts delete button. **/
    public Button partsDeleteBtn;

    /** Products add button. **/
    public Button productsAddBtn;

    /** Products modify button. **/
    public Button productsModifyBtn;

    /** Products delete button. **/
    public Button productsDeleteBtn;

    /** Exit button. **/
    public Button exitBtn;

    //VARIABLES
    /** Stage variable of type Stage.
     *
     * Sets the stage for the scene. **/
    Stage stage;

    /** Scene variables of type Parent.
     *
     * Sets the scene for the user. **/
    Parent scene;

    /** Part variable called highlightedPart. **/
    private static Part highlightedPart;

    /** Product variable called highlightedProduct. **/
    private static Product highlightedProduct;

    //METHODS

    /** Gets highlighted part.
     *
     * Grabs the part that is highlighted and returns it.
     *
     * @return highlightedPart. **/
    public static Part getHighlightedPart() {
        return highlightedPart;
    }

    /** Gets highlighted product.
     *
     * Grabs the product that is highlighted and returns it.
     *
     * @return highlightedProduct. **/
    public static Product getHighlightedProduct() {
        return highlightedProduct;
    }

    /** Exit button closes the application.
     *
     * @param actionEvent Exit button called.**/
    public void onActionExit(ActionEvent actionEvent) {
        //HANDLES THE CLOSE OF THE APPLICATION
        System.exit(0);
    }

    /** Moves user to the add product screen.
     *
     * When the product add button is called, the user is moved the to add products screen.
     *
     * @param actionEvent Add button called. **/
    public void onActionProductsAdd(ActionEvent actionEvent) throws IOException {
        //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addProductForm.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();
    }

    /** Moves user to the add part screen.
     *
     * When the parts add button is called, the user is moved to the add parts screen.
     *
     * @param actionEvent Add button called. **/
    public void onActionAddPart(ActionEvent actionEvent) throws IOException {

        //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addPartForm.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();
    }

    /** Moves user the the modify part screen.
     *
     * If there is a part highlighted, the user is moved to the modify part screen, and the part information if pulled with it.
     *
     * RUNTIME ERROR: If part was not highlighted, the application would crash. FIX: Add an error display to pop up when a part is not highlighted.
     *
     * @param actionEvent Modify button called. **/
    public void onActionModifyPart(ActionEvent actionEvent) throws IOException {

        highlightedPart = partsTbl.getSelectionModel().getSelectedItem();//THIS GRABS THE HIGHLIGHTED PART

        if (highlightedPart == null) {
            alertDisplays(0);
        } else {
            //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/modifyPartForm.fxml"));
            stage.setScene(new Scene(scene));//sets up the scene
            stage.show();
        }
    }


    /** Moves user the the modify product screen.
     *
     * If there is a product highlighted, the user is moved to the modify product screen, and the product information if pulled with it.
     *
     * RUNTIME ERROR: If product was not highlighted, the application would crash. FIX: Add an error display to pop up when a product is not highlighted.
     *
     * @param actionEvent Modify button called. **/
    public void onActionModifyProduct(ActionEvent actionEvent) throws IOException {

        highlightedProduct = productsTbl.getSelectionModel().getSelectedItem();

        if (highlightedProduct == null) {
            alertDisplays(1);
        } else {
            //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/modifyProductForm.fxml"));
            stage.setScene(new Scene(scene));//sets up the scene
            stage.show();
        }
    }

    /** Deletes the highlighted part.
     *
     * Deletes the highlighted part with confirmation prompt.
     *
     * @param actionEvent Delete button called. **/
    public void onActionDeletePart(ActionEvent actionEvent) {

        Part highlightedPart = partsTbl.getSelectionModel().getSelectedItem();//THIS SHOULD GET THE HIGHLIGHTED PART

        if (highlightedPart == null) {
            alertDisplays(0);
        } else {
            //DISPLAYS THE CONFIRMATION FOR THE DELETE SCREEN
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Parts");
            alert.setContentText("Do you wish to delete this part?");
            Optional<ButtonType> answer = alert.showAndWait();

            if (answer.isPresent() && answer.get() == ButtonType.OK) {
                Inventory.deletePart(highlightedPart);
            }
        }
    }

    /** Deletes the highlighted product.
     *
     * Deletes the highlighted product with confirmation prompt. If there is an associated part, the user will be prompted with a not able to delete screen.
     *
     * @param actionEvent Delete button called. **/
    public void onActionDeleteProduct(ActionEvent actionEvent) {
        Product highlightedProduct = productsTbl.getSelectionModel().getSelectedItem();//THIS SHOULD GET THE HIGHLIGHTED PRODUCT

        if (highlightedProduct == null) {
            alertDisplays(1);
        } else {
            //DISPLAYS THE CONFIRMATION FOR THE DELETE SCREEN
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Products");
            alert.setContentText("Do you wish to delete this product?");
            Optional<ButtonType> answer = alert.showAndWait();

            if (answer.isPresent() && answer.get() == ButtonType.OK) {

                ObservableList<Part> associatedPart = highlightedProduct.getAllAssociatedParts();

                //THIS WILL MAKE SURE THAT YOU CANNOT DELETE A PRODUCT WITH ASSOCIATED PARTS
                if (associatedPart.size() >= 1) {
                    alertDisplays(2);
                } else {
                    Inventory.deleteProduct(highlightedProduct);
                }
            }
        }
    }

    /** Initialize method that populates parts table and products table. **/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //POPULATING THE PARTS TABLE
        partsTbl.setItems(Inventory.getAllParts());
        partsTblPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsTblPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsTblInvLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsTblPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        //POPULATING THE PRODUCTS TABLE
        productsTbl.setItems(Inventory.getAllProducts());
        productsTblProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productsTblProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsTblInvLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsTblPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /** Searches for parts by Part ID or Name.
     *
     * User can search by both ID and name. Name can be partial or verbatim.
     *
     * @param actionEvent Search bar text field called. **/
    public void onActionSearchParts(ActionEvent actionEvent) {

        try {
            int partId = Integer.parseInt(partsSearchBar.getText());
            partsTbl.getSelectionModel().select(Inventory.lookupPart(partId));
        } catch (Exception e) {
            String searched = partsSearchBar.getText();
            ObservableList<Part> part = Inventory.lookupPart(searched);
            partsTbl.setItems(part);
        }
    }

    /** Searches for products by Product ID or Name.
     *
     * User can search by both ID and name. Name can be partial or verbatim.
     *
     * @param actionEvent Search bar text field called. **/
    public void onActionProductsSearch(ActionEvent actionEvent) {

        try {
            int productId = Integer.parseInt(productsSearchBar.getText());
            productsTbl.getSelectionModel().select(Inventory.lookupProduct(productId));
        } catch (Exception e) {
            String searched = productsSearchBar.getText();
            ObservableList<Product> product = Inventory.lookupProduct(searched);
            productsTbl.setItems(product);
        }
    }

    /** Displays various error messages.
     *
     * Based on the input, displays the appropriate error message instead of crashing program.
     *
     * @param alertType Alert message selector. **/
    private void alertDisplays(int alertType) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);

        switch (alertType) {
            case 0:
                alert.setTitle("Error");
                alert.setHeaderText("Part was not highlighted");
                alert.showAndWait();
                break;
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Product was not highlighted");
                alert.showAndWait();
                break;
            case 2:
                alertInfo.setTitle("Error");
                alertInfo.setHeaderText("Parts are Associated with this Product");
                alertInfo.setContentText("You must delete all parts associated with this product before deletion can be successful");
                alertInfo.showAndWait();
                break;
        }
    }
}//END OF CLASS

