package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Marshall Christian : 5/24/2021
 * **/

public class Product {

    //CONSTRUCTOR

    /** Default constructor.
     *
     * @param id Product ID.
     * @param name Product Name.
     * @param stock Product Inventory Level.
     * @param price Product Price.
     * @param min Product Min
     * @param max Product Max. **/
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    //VARIABLES

    /** Product ID. **/
    int id;

    /** Product Name. **/
    String name;

    /** Product Price. **/
    double price;

    /** Product Inventory Level. **/
    int stock;

    /** Product Min. **/
    int min;

    /** Product Max. **/
    int max;

    /** Observable list of type Parts called associatedParts. **/
    ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    //GETTERS

    /** Gets product ID.
     *
     * @return Product ID.**/
    public int getId() {
        return id;
    }

    /** Gets product Name.
     *
     * @return Product Name.**/
    public String getName() {
        return name;
    }

    /** Gets product Price.
     *
     * @return Product Price.**/
    public double getPrice() {
        return price;
    }

    /** Gets product Inventory Level.
     *
     * @return Product Inventory Level.**/
    public int getStock() {
        return stock;
    }

    /** Gets product Min.
     *
     * @return Product Min.**/
    public int getMin() {
        return min;
    }

    /** Gets product Max.
     *
     * @return Product Max.**/
    public int getMax() {
        return max;
    }

    //SETTERS

    /** Sets Product ID. **/
    public void setId(int id) {
        this.id = id;
    }

    /** Sets Product Name. **/
    public void setName(String name) {
        this.name = name;
    }

    /** Sets Product Price. **/
    public void setPrice(double price) {
        this.price = price;
    }

    /** Sets Product Inventory Level. **/
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** Sets Product Min. **/
    public void setMin(int min) {
        this.min = min;
    }

    /** Sets Product Max. **/
    public void setMax(int max) {
        this.max = max;
    }

    //METHODS

    /** Adds parts to the associated parts observable list.
     *
     * @param part Part. **/
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /** Deletes selected part from the associated parts observable list.
     *
     * @param selectedAssociatedPart Highlighted part. **/
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        if (associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        return false;
    }

    /** Gets all associated parts from the associatedParts observable list. **/
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}//END OF CLASS
