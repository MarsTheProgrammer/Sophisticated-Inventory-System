package model;

/**
 * @author Marshall Christian : 5/24/2021
 * **/

public class Outsourced extends Part {

    /** Default construct with company name variable added. **/
    //DEFAULT SUPER CONSTRUCTOR WITH COMPANY NAME VARIABLE ADDED
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    //VARIABLES

    /** Outsourced Company Name. **/
    String companyName;

    //GETTER

    /** Gets Company Name.
     *
     * @return Part Company Name. **/
    public String getCompanyName() {
        return companyName;
    }

    //SETTER

    /** Sets Company Name
     *
     * @param companyName Part Company Name. **/
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
} //END OF CLASS
