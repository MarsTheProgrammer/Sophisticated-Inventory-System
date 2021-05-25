package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    //VARIABLES
    private static int uniquePartId = 0;
    private static int uniqueProductId = 0;
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    //METHODS
    public static int getUniquePartId() {
        return ++uniquePartId;
    }
    public static int getUniqueProductId() {
        return ++uniqueProductId;
    }

    public static void addPart(Part newPart) {

        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {

        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId) {
        Part isPartFound = null;
        //SETS THE IS_PART_FOUND TO EMPTY IN CASE IT IS NOT FOUND
        for(Part part : allParts) {//FOR EVERY PART IN allParts
            if (part.getId() == partId) {//CHECK IF ID MATCHES GIVEN partId
                isPartFound = part;//IF SO, THEN IT IS
            }
        }
        return isPartFound;//RETURN EITHER THE NULL PART, OR THE PART THAT WAS FOUND
    }

    public static Product lookupProduct(int productId) {

        //SAME AS ABOVE, JUST FOR PRODUCT
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName) {

        ObservableList<Part> searchedPart = FXCollections.observableArrayList();//EMPTY LIST

        for (Part part : allParts) {
            if (part.getName().contains(partName)) {
                searchedPart.add(part);
            }
        }

        if (searchedPart.isEmpty()) {
            return allParts;
        }
        return searchedPart;
    }

    public static ObservableList<Product> lookupProduct(String productName) {

        ObservableList<Product> searchProducts = FXCollections.observableArrayList();//EMPTY LIST

        for (Product product : allProducts) {
            if (product.getName().contains(productName)) {
                searchProducts.add(product);
            }
        }

        if (searchProducts.isEmpty()) {
            return allProducts;
        }
        return searchProducts;
    }

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
        //SETS THE SELECTED PART TO THE INDEX GIVEN
    }

    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
        //SAME AS ABOVE BUT WITH PRODUCT
    }

    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
        } else {
            return false;
        }
        return true;
    }

    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
        } else {
            return false;
        }
        return true;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() { return allProducts; }




}//END OF CLASS
