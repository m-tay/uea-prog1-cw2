package tollroadmain;

import java.util.HashMap;

public class TollRoad {
    private HashMap<Integer, CustomerAccount> customers = new HashMap();
    private int moneyMade;
    
    // Constructor
    public TollRoad() {
        moneyMade = 0;
    }
    
    public void addCustomer(CustomerAccount customer) {
        customers.put(customer.getRegNum().hashCode(), customer);
    }
    
    public CustomerAccount findCustomer(String regNum) throws CustomerNotFoundException { 
        if(customers.containsKey(regNum.hashCode()))
            return customers.get(regNum.hashCode());
        else
            throw new CustomerNotFoundException();           
        }
        
    public void chargeCustomer(String registrationNumber) throws Exception {
        CustomerAccount foundCust;
        
        try {
            foundCust = findCustomer(registrationNumber);
            
            try {
                this.moneyMade = moneyMade + foundCust.makeTrip();
            } catch (InsufficientAccountBalanceException e) {
                throw new InsufficientAccountBalanceException();
            }
            
        } catch (CustomerNotFoundException e) {
            throw new CustomerNotFoundException();
        }
    }
    
    public int getMoneyMade() {
        return moneyMade;
    }
    
    // Test harness
    public static void main(String args[]) {
        // Testing addCustomer()
        TollRoad testRoad = new TollRoad();        
        Car testCar1 = new Car("AA11 AAA", "Ford", 5);
        CustomerAccount testAcc1 = new CustomerAccount("Joe", "Bloggs", 0, testCar1);
        
        System.out.println("customers size: " + testRoad.customers.size());
        testRoad.addCustomer(testAcc1);
        System.out.println("1 customer added, customers size: " + testRoad.customers.size());
        System.out.println("First name: " + testRoad.customers.get(0).getFirstName());
        System.out.println("Last name : " + testRoad.customers.get(0).getLastName());
        System.out.println("Reg num   : " + testRoad.customers.get(0).getRegNum());
        System.out.println("Balance   : " + testRoad.customers.get(0).getBalance());        
        
        // Testing findCustomer
        Van testVan1 = new Van("VA55 NAN", "Renault", 900);
        CustomerAccount testAcc2 = new CustomerAccount("James", "Gosling", 0, testVan1);
        testRoad.addCustomer(testAcc2);
        
        CustomerAccount foundCustomer;
        try {
            foundCustomer = testRoad.findCustomer("VA55 NAN");
            System.out.println("foundCustomer name: " 
                                + foundCustomer.getFirstName() + " " 
                                + foundCustomer.getLastName() + " (" 
                                + foundCustomer.getRegNum() + ")");
                  
        } catch (CustomerNotFoundException e) {
            System.out.println("Error: customer not found");
        }
        
        // Testing chargeCustomer()
        System.out.println("Money made: " + testRoad.moneyMade); 
        
        // insufficientbalance
        try {
            testRoad.chargeCustomer("AA11 AAA"); 
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        System.out.println("Money made: " + testRoad.moneyMade); 
        
        // moneyMade + 1000
        try {
            testRoad.findCustomer("VA55 NAN").addFunds(1500);
            testRoad.chargeCustomer("VA55 NAN"); 
        } catch (Exception e) {
            System.out.println(e);
        }
        
        System.out.println("Money made: " + testRoad.moneyMade); 
        
        // exception
        try {
            testRoad.chargeCustomer("ZX67 CVB"); 
        } catch (Exception e) {
            System.out.println(e);
        }
        
        System.out.println("Money made: " + testRoad.moneyMade); 

   }
}