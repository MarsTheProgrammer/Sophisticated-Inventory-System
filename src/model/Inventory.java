package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * @author Marshall Christian : 5/24/2021
 * **/

public class Inventory {

    //VARIABLES

    /** Unique Part ID. **/
    private static int uniquePartId = 0;

    /** Unique Product ID. **/
    private static int uniqueProductId = 0;

    /** Observable list of type Part called and containing allParts. **/
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /** Observable list of type Product called and containing allProducts. **/
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    //METHODS

    /** Gets unique Part ID.
     *
     * Increments the ID before getting to make sure ID's are unique.
     *
     * @return Unique Part ID. **/
    public static int getUniquePartId() {
        return ++uniquePartId;
    }

    /** Gets unique Product ID.
     *
     * Increments the ID before getting to make sure ID's are unique.
     *
     * @return Unique Product ID. **/
    public static int getUniqueProductId() {
        return ++uniqueProductId;
    }

    /** Adds part to the allParts observable list.
     *
     * @param newPart New part. **/
    public static void addPart(Part newPart) {

        allParts.add(newPart);
    }

    /** Add product to the allProducts observable list.
     *
     * @param newProduct New Product. **/
    public static void addProduct(Product newProduct) {

        allProducts.add(newProduct);
    }

    /** Looks up parts based on Part ID.
     *
     * @param partId Part ID. **/
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

    /** Looks up products based on Product ID.
     *
     * @param productId Product ID. **/
    public static Product lookupProduct(int productId) {

        //SAME AS ABOVE, JUST FOR PRODUCT
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /** Looks up parts based on Part Name.
     *
     * @param partName Part Name.
     * @return Search Part. **/
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

    /** Looks up products based on Product Name.
     *
     * @param productName Product Name.
     * @return Search Product. **/
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

    /** Updates part based on index and highlighted part.
     *
     * @param index Index of allParts observable list.
     * @param selectedPart Selected Part. **/
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
        //SETS THE SELECTED PART TO THE INDEX GIVEN
    }

    /** Updates product based on index and highlighted product.
     *
     * @param index Index of allProducts observable list.
     * @param newProduct Selected Product. **/
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
        //SAME AS ABOVE BUT WITH PRODUCT
    }

    /** Deletes parts by highlighted part.
     *
     * @param selectedPart Highlighted Part.
     * @return true. **/
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
        } else {
            return false;
        }
        return true;
    }

    /** Deletes products based upon highlighted product.
     *
     * @param selectedProduct Highlighted Product.
     * @return true. **/
    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
        } else {
            return false;
        }
        return true;
    }

    /** Gets all parts from the allParts observable list of type Part.
     *
     * @return Observable List called allParts. **/
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /** Gets all products from the allProducts observable list of type Product.
     *
     * @return Observable List called allProducts. **/
    public static ObservableList<Product> getAllProducts() { return allProducts; }
}//END OF CLASS
