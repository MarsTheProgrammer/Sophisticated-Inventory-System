package model;

import javafx.collections.ObservableList;

public class Inventory {

    //VARIABLES
    private static ObservableList<Part> allParts;
    private static ObservableList<Product> allProducts;

    //METHODS
    public static void addPart(Part newPart) {

        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {

        allProducts.add(newProduct);
    }

//    public static Part lookupPart(int partId) {
//
//    }

//    public static Product lookupProduct(int productId) {
//
//    }

//    public static ObservableList<Part> lookupPart(String partName) {
//
//    }

//    public static ObservableList<Product> lookupProduct(String productName) {
//
//    }
//
//    public static void updatePart(int index, Part selectedPart) {
//
//    }
//
//    public static void updateProduct(int index, Product newProduct) {
//
//    }
//
//    public static boolean deletePart(Part selectedPart) {
//        return true;
//    }
//
//    public static boolean deleteProduct(Product selectedProduct) {
//        return true;
//    }

    public static ObservableList<Part> getAllParts() {

        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {

        return allProducts;
    }


}//END OF CLASS
