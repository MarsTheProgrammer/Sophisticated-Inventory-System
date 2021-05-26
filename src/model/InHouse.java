package model;

/**
 * @author Marshall Christian : 5/24/2021
 * **/

public class InHouse extends Part {

    /** Default construct with machine id variable added. **/
    //DEFAULT SUPER CONSTRUCTOR WITH MACHINE ID VARIABLE ADDED
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    //VARIABLES

    /** In-House Machine ID. **/
    private int machineId;

    //GETTER

    /** Gets Machine ID.
     *
     * @return Part Machine ID. **/
    public int getMachineId() {
        return machineId;
    }

    //SETTER

    /** Sets Machine ID
     *
     * @param machineId Part Machine ID. **/
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}//END OF CLASS
