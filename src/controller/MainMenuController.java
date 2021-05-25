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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class MainMenuController implements Initializable {

    //@FXML VARIABLES
    public TextField partsSearchBar;
    public TextField productsSearchBar;
    public TableView<Part> partsTbl;
    public TableColumn<Part, Integer> partsTblPartId;
    public TableColumn<Part, String> partsTblPartName;
    public TableColumn<Part, Integer> partsTblInvLevel;
    public TableColumn<Part, Double> partsTblPrice;
    public TableView<Product> productsTbl;
    public TableColumn<Product, Integer> productsTblProductId;
    public TableColumn<Product, String> productsTblProductName;
    public TableColumn<Product, Integer> productsTblInvLevel;
    public TableColumn<Product, Double> productsTblPrice;
    public Button partsAddBtn;
    public Button partsModifyBtn;
    public Button partsDeleteBtn;
    public Button productsAddBtn;
    public Button productsModifyBtn;
    public Button productsDeleteBtn;
    public Button exitBtn;

    //VARIABLES
    Stage stage;
    Parent scene;
    private static Part highlightedPart;
    private static Product highlightedProduct;
//    ObservableList<Part> grabbedPart = FXCollections.observableArrayList();

    //METHODS


    public static Part getHighlightedPart() {
        return highlightedPart;
    }

    public static Product getHighlightedProduct() {
        return highlightedProduct;
    }

    public void onActionExit(ActionEvent actionEvent) {
        //HANDLES THE CLOSE OF THE APPLICATION
        System.exit(0);
    }

    public void onActionProductsAdd(ActionEvent actionEvent) throws IOException {
        //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addProductForm.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();
    }

    public void onActionAddPart(ActionEvent actionEvent) throws IOException {

        //THIS ALLOWS US TO SWITCH SCREENS WHEN BUTTON IS PRESSED
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addPartForm.fxml"));
        stage.setScene(new Scene(scene));//sets up the scene
        stage.show();
    }

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

    public void onActionDeleteProduct(ActionEvent actionEvent) {
        Product highlightedProduct = productsTbl.getSelectionModel().getSelectedItem();//THIS SHOULD GET THE HIGHLIGHTED PRODUCTS

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

    public void onMouseClickedSearchbar(MouseEvent mouseEvent) {
        //WE NEED TO CLEAR THE TEXT FIELD WHEN PRESSED

        productsSearchBar.clear();
    }


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
                //INFORMATIVE ERROR
                alertInfo.setTitle("Error");
                alertInfo.setHeaderText("Part are Associated with this Product");
                alertInfo.setContentText("You must delete all parts associated with this product before deletion can be successful");
                alertInfo.showAndWait();
                break;
            case 3:
                alert.setTitle("3");
                alert.setHeaderText("3");
                alert.setContentText("3");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("4");
                alert.setHeaderText("4");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("5");
                alert.setHeaderText("5");
                alert.setContentText("5");
                alert.showAndWait();
                break;
        }
    }
}//END OF CLASS

