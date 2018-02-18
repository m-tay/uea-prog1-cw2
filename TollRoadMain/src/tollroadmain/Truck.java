package tollroadmain;

public class Truck extends Vehicle {
    
    private int numTrailers;
    
    // Constructor
    public Truck(String regPlate, String make, int trailers) {
        super(regPlate, make);
        this.numTrailers = trailers;
    }
    
    @Override
    public int calculateBasicTripCost() {
        if (numTrailers <= 1) // Assume truck with 0 trailers is charged truck base rate
            return 1250;
        else
            return 1500;
    }
    
    public int getTrailers() {
        return numTrailers;
    }

    public static void main(String args[]) {
        Truck testTruck1 = new Truck("AS34 DFG", "DAF", 1);
        System.out.println("testTruck1 reg     : " + testTruck1.getRegPlate());
        System.out.println("testTruck1 make    : " + testTruck1.getMake());
        System.out.println("testTruck1 trailers: " + testTruck1.getTrailers());
        Truck testTruck2 = new Truck("AS34 DFG", "DAF", 2); // should return 1500
        System.out.println("testTruck1 cost    : " + testTruck1.calculateBasicTripCost());
        System.out.println("testTruck2 cost    : " + testTruck2.calculateBasicTripCost());        
     }
}

