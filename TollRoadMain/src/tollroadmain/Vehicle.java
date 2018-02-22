package tollroadmain;

abstract public class Vehicle {
    
    protected String regPlate;
    private String make;
    
    // Constructor
    public Vehicle(String regPlate, String make) {
        this.regPlate = regPlate;
        this.make = make;
    }
    
    abstract public int calculateBasicTripCost();
    
    // Accessor methods
    String getRegPlate() {
        return regPlate;
    }
    
    String getMake() {
        return make;
    } 
}
