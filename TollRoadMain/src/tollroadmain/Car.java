package tollroadmain;

public class Car extends Vehicle {
    
    private final int numberOfSeats;
    
    // Constructor
    public Car(String regPlate, String make, int seats) {
        super(regPlate, make);      
        this.numberOfSeats = seats;        
    }
    
    @Override
    public int calculateBasicTripCost() {
        if (numberOfSeats <= 5)
            return 500;
        else
            return 600;
    }
    
    int getNumOfSeats() {
        return numberOfSeats;
    }
   
    // Test harness
    public static void main(String args[]) {
        Car testCar1 = new Car("AB12 CDE", "Ford", 5);                
        System.out.println("testCar1 reg   : " + testCar1.getRegPlate());
        System.out.println("testCar1 make  : " + testCar1.getMake());
        System.out.println("testCar1 seats : " + testCar1.getNumOfSeats());
        
        Car testCar2 = new Car("AB12 CDE", "Ford", 4); // should return 500
        Car testCar3 = new Car("AB12 CDE", "Ford", 6); // should return 600
        
        System.out.println("testCar1 cost  : " + testCar1.calculateBasicTripCost());
        System.out.println("testCar2 cost  : " + testCar2.calculateBasicTripCost());
        System.out.println("testCar3 cost  : " + testCar3.calculateBasicTripCost());   
    }
}
