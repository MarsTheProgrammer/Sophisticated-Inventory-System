package model;

import model.Part;

public class Outsourced extends Part {

    //DEFAULT SUPER CONSTRUCTOR WITH COMPANYNAME VARIABLE ADDED
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    //VARIABLES
    String companyName;

    //GETTER
    public String getCompanyName() {
        return companyName;
    }

    //SETTER
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

} //END OF CLASS
