package tollroadmain;

public class CustomerAccount implements Comparable<CustomerAccount> { 
    private enum Discount {NONE, STAFF, FRIENDS_AND_FAMILY}
    
    private String firstName;
    private String lastName;
    private int balance;
    private Discount discount;
    private Vehicle vehicle;
    
    // Constructor
    public CustomerAccount(String firstName, String lastName, int balance, Vehicle vehicle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        discount = Discount.NONE; 
        this.vehicle = vehicle;
    }
    
    public void activateStaffDiscount() {
        discount = Discount.STAFF;
    }
    
    public void activateFriendsAndFamilyDiscount() {
        if (discount != Discount.STAFF)
            discount = Discount.FRIENDS_AND_FAMILY;
    }
    
    public void deactivateDiscount() { 
       discount = Discount.NONE;
    }
    
    public void addFunds(int addedFunds) {
        balance = balance + addedFunds;
    }
    
    public int makeTrip() throws InsufficientAccountBalanceException {
        int cost = vehicle.calculateBasicTripCost();
        
        if(discount == Discount.STAFF)
            cost = cost / 2;        // 50% discount
        else if(discount == Discount.FRIENDS_AND_FAMILY)
            cost = (cost / 10) * 9; // 90% discount

        if (balance - cost >= 0) { // if sufficient funds
            balance = balance - cost;
        }
        else {
            throw new InsufficientAccountBalanceException();            
        }
    
        return cost;
    }
    
    @Override
    public int compareTo(CustomerAccount other) {
        return vehicle.getRegPlate().compareTo(other.vehicle.getRegPlate());
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
    
    public String getRegNum() {
        return vehicle.getRegPlate();
    }
     
    // Test harness
    public static void main(String args[]) {
        Car testCar1 = new Car("AA11 AAA", "Ford", 5);
        CustomerAccount testAcc1 = new CustomerAccount("Joe", "Bloggs", 500, testCar1);
        
        // Testing accessor methods
        System.out.println("testAcc1 first name: " + testAcc1.firstName);
        System.out.println("testAcc1 last name : " + testAcc1.lastName);
        System.out.println("testAcc1 balance   : " + testAcc1.balance);
        
        // Testing discounts
        System.out.println("testAcc1 discount: " + testAcc1.discount); // should be NONE (by default)
        testAcc1.activateStaffDiscount(); 
        System.out.println("testAcc1 discount: " + testAcc1.discount); // should be STAFF
        testAcc1.deactivateDiscount();
        System.out.println("testAcc1 discount: " + testAcc1.discount); // should be NONE (after deactivation)
        testAcc1.activateFriendsAndFamilyDiscount();
        System.out.println("testAcc1 discount: " + testAcc1.discount); // should be FRIENDS_AND_FAMILY
        
        // Testing adding funds
        testAcc1.addFunds(550);
        System.out.println("testAcc1 funds: " + testAcc1.getBalance()); // should be 500
        
        // Testing makeTrip function (for 5 door car with no discount)
        int tripCost = -1; 
        testAcc1.deactivateDiscount();
        
        try { // Balance starts 550, should end on 50
            tripCost = testAcc1.makeTrip();
        } catch(InsufficientAccountBalanceException e) {
            System.out.println("Insufficient account balance!");
        } finally {
            System.out.println("Trip cost : " + tripCost);
            System.out.println("testAcc1 balance: " + testAcc1.getBalance());
        }
        
        try { // Balance starts 50, should throw exception
            tripCost = testAcc1.makeTrip();
        } catch(InsufficientAccountBalanceException e) {
            System.out.println("Insufficient account balance!");
        } finally {
            System.out.println("Trip cost : " + tripCost);
            System.out.println("testAcc1 balance: " + testAcc1.getBalance());
        }
        
        // Testing makeTrip() and staff discount (for 750kg van)
        Van testVan1 = new Van("VA55 NAN", "Renault", 700);
        CustomerAccount testAccVan = new CustomerAccount("James", "Gosling", 1000, testVan1);
        testAccVan.activateStaffDiscount();
        
        tripCost = -1; // Reset testing variable
        testAccVan.addFunds(500);
        
        try { // Balance starts 500, should end on 125
            tripCost = testAccVan.makeTrip();
        } catch(InsufficientAccountBalanceException e) {
            System.out.println("Insufficient account balance!");
        } finally {
            System.out.println("Trip cost : " + tripCost);
            System.out.println("testVan1 balance: " + testAccVan.getBalance());
        }
        
        try { // Balance should start at 125, should throw exception
            tripCost = testAccVan.makeTrip();
        } catch(InsufficientAccountBalanceException e) {
            System.out.println("Insufficient account balance!");
        } finally {
            System.out.println("Trip cost : " + tripCost);
            System.out.println("testVan1 balance: " + testAccVan.getBalance());
        }
        
        // Testing makeTrip and friends discount (for 1 trailer truck)
        Truck testTruck1 = new Truck("TR79 UCK", "IVECO", 1);
        CustomerAccount testAccTruck = new CustomerAccount("Scott", "McNealy", 2000, testTruck1);
        testAccTruck.activateFriendsAndFamilyDiscount();
        
        tripCost = -1; // Reset testing variable
        testAccTruck.addFunds(2000);
        
        try { // Balance starts 2000, should end 875
            tripCost = testAccTruck.makeTrip();
        } catch(InsufficientAccountBalanceException e) {
            System.out.println("Insufficient account balance!");
            System.out.println("Trip cost : " + tripCost);
            System.out.println("testAccTruck balance: " + testAccTruck.getBalance());
        }    

        try { // Balance should start at 875, should throw exception
            tripCost = testAccTruck.makeTrip();
        } catch(InsufficientAccountBalanceException e) {
            System.out.println("Insufficient account balance!");
        } finally {
            System.out.println("Trip cost : " + tripCost);
            System.out.println("testAccTruck balance: " + testAccTruck.getBalance());
        }            
        
        
        // Testing compareTo implementation
        Car testCar2 = new Car("BB22 BBB", "Vauxhall", 3);  
        CustomerAccount testAcc2 = new CustomerAccount("Steve", "McQueen", 1500, testCar2);
        
        Car testCar3 = new Car("AA11 AAA", "Seat", 3);  
        CustomerAccount testAcc3 = new CustomerAccount("Donald", "Knuth", 2000, testCar3);

        // Should return -1 (first reg < other reg)
        System.out.println("testAcc1 compared to testAcc2 : " + testAcc1.compareTo(testAcc2));
        
        // Should return 0 (first reg == other reg)
        System.out.println("testAcc1 compared to testAcc3 : " + testAcc1.compareTo(testAcc3));
        
        // Should return 1 (first reg > other reg)
        System.out.println("testAcc2 compared to testAcc1 : " + testAcc2.compareTo(testAcc1));
        
        
    }
}

