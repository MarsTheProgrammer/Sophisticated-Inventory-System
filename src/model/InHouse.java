package model;

import model.Part;

public class InHouse extends Part {

    //DEFAULT SUPER CONSTRUCTOR WITH MACHINEID VARIABLE ADDED
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    //VARIABLES
    private int machineId;

    //GETTER
    public int getMachineId() {
        return machineId;
    }

    //SETTER

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

}//END OF CLASS
