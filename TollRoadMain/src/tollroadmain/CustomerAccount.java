package tollroadmain;

public class CustomerAccount implements Comparable<CustomerAccount> { 
    private enum Discount {NONE, STAFF, FRIENDS_AND_FAMILY}
    
    private String firstName;
    private String lastName;
    private int balance;
    private Discount discount;
    private Vehicle vehicle;
    
    // Constructor
    public CustomerAccount(String firstName, String lastName, Vehicle vehicle) {
        this.firstName = firstName;
        this.lastName = lastName;
        balance = 0; // Assuming accounts always start with no balance
        this.vehicle = vehicle;
    }
    
    public void activateStaffDiscount() {
        discount = Discount.STAFF;
    }
    
    public void activateFriendsAndFamilyDiscount() {
        discount = Discount.FRIENDS_AND_FAMILY;
    }
    
    public void deactivateDiscount() {
        discount = Discount.NONE;
    }
    
    public void addFunds(int addedFunds) {
        balance = balance + addedFunds;
    }
    
    public int makeTrip() {
        int cost = vehicle.calculateBasicTripCost();
        
        if(discount == Discount.STAFF)
            cost = cost / 2;        // 50% discount
        else if(discount == Discount.FRIENDS_AND_FAMILY)
            cost = (cost / 10) * 9; // 90% discount
        
        return cost;
    }
    
    public int compareTo(CustomerAccount other) {
        return vehicle.regPlate.compareTo(other.vehicle.regPlate);
    }
    
    // Accessor methods
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public int getBalance() {
        return balance;
    }
    
    // Test harness
    public static void main(String args[]) {
        Car testCar1 = new Car("AA11 AAA", "Ford", 5);  
        CustomerAccount testAcc1 = new CustomerAccount("Joe", "Bloggs", testCar1);
        
        // Testing accessor methods
        System.out.println("testAcc1 first name: " + testAcc1.firstName);
        System.out.println("testAcc1 last name : " + testAcc1.lastName);
        System.out.println("testAcc1 balance   : " + testAcc1.balance);
        
        // Testing compareTo implementation
        Car testCar2 = new Car("BB22 BBB", "Vauxhall", 3);  
        CustomerAccount testAcc2 = new CustomerAccount("Steve", "McQueen", testCar2);
        
        Car testCar3 = new Car("AA11 AAA", "Seat", 3);  
        CustomerAccount testAcc3 = new CustomerAccount("Donald", "Knuth", testCar3);

        // Should return -1 (first reg < other reg)
        System.out.println("testAcc1 compared to testAcc2 : " + testAcc1.compareTo(testAcc2));
        
        // Should return 0 (first reg == other reg)
        System.out.println("testAcc1 compared to testAcc3 : " + testAcc1.compareTo(testAcc3));
        
        // Should return 1 (first reg > other reg)
        System.out.println("testAcc2 compared to testAcc1 : " + testAcc2.compareTo(testAcc1));
        
        
    }
}

