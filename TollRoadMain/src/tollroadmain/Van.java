package tollroadmain;

public class Van extends Vehicle {
    
    private final int payload;  // kilograms
    
    // Constructor
    public Van(String regPlate, String make, int payload) {
        super(regPlate, make);
        this.payload = payload;
    }
    
    @Override
    public int calculateBasicTripCost() {
        if (payload <= 600)
            return 500;
        else if (payload <= 800)
            return 750;
        else
            return 1000;
    }
    
    public int getPayload() {
        return payload;       
    }
    
    @Override
    public String toString() {
        return "Reg: " + regPlate + " | Make: " + make + " | Payload: " + payload;
    }
    
    
    // Test harness
    public static void main(String args[]) {
        Van testVan1 = new Van("QW09 ERT", "Renault", 250);
        System.out.println("testVan1 reg     : " + testVan1.getRegPlate());
        System.out.println("testVan1 make    : " + testVan1.getMake());
        System.out.println("testVan1 payload : " + testVan1.getPayload());
        
        Van testVan2 = new Van("QW09 ERT", "Renault", 600); // Should return 500    
        Van testVan3 = new Van("QW09 ERT", "Renault", 750); // Should return 750
        Van testVan4 = new Van("QW09 ERT", "Renault", 800); // Should return 750
        Van testVan5 = new Van("QW09 ERT", "Renault", 900); // Should return 1000 
        
        System.out.println(testVan1);
        
        System.out.println("testVan1 cost    : " + testVan1.calculateBasicTripCost());
        System.out.println("testVan2 cost    : " + testVan2.calculateBasicTripCost());
        System.out.println("testVan3 cost    : " + testVan3.calculateBasicTripCost());
        System.out.println("testVan4 cost    : " + testVan4.calculateBasicTripCost());
        System.out.println("testVan5 cost    : " + testVan5.calculateBasicTripCost());
    }
}
