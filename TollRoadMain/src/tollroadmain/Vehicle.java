package tollroadmain;

abstract public class Vehicle {
    
    protected String regPlate;
    protected String make;
    
    // Constructor
    public Vehicle(String regPlate, String make) {
        this.regPlate = regPlate;
        this.make = make;
    }
    
    abstract public int calculateBasicTripCost();
    
    // Accessor methods
    public String getRegPlate() {
        return regPlate;
    }
    
    public String getMake() {
        return make;
    } 
}
