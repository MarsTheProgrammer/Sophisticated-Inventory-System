package model;

import javafx.collections.ObservableList;
import model.Part;

public class Product {

    int id;
    String name;
    double price;
    int stock;
    int min;
    int max;
    ObservableList<Part> associatedParts;

    //CONSTRUCTOR
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    //GETTERS

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    //SETTERS


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    //METHODS

    public void addAssociatedPart(Part part) {

    }

    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {

        return true;//for now to remove the error
    }

    public ObservableList<Part> getAllAssociatedParts() {

        return associatedParts;
    }


}//END OF CLASS
