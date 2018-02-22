package tollroadmain;

import java.util.ArrayList;

public class TollRoad {
    private ArrayList<CustomerAccount> customers = new ArrayList();
    private int moneyMade;
    
    // Constructor
    public TollRoad() {
        moneyMade = 0;
    }
    
    public void addCustomer(CustomerAccount customer) {
        customers.add(customer);
    }
    
    public CustomerAccount findCustomer(String regNum) throws CustomerNotFoundException { 
        // Store index of CustomerAccount in here, -1 if nothing found
        int customerIndex = -1; 
        
        // Iterate through customers, save index to customerIndex if found
        for(int i=0; i<customers.size(); i++) {
            if (customers.get(i).getRegNum().equals(regNum))
                customerIndex = i;
        }
        
        // If a customer was found, customerIndex won't be -1
        if(customerIndex > -1)
            return customers.get(customerIndex);
        else
            throw new CustomerNotFoundException();           
    }
    
    public void chargeCustomer(String registrationNumber) throws Exception {
        CustomerAccount foundCust;
        
        try {
            foundCust = findCustomer(registrationNumber);
            
            try {
                this.moneyMade += foundCust.makeTrip();
            } catch (InsufficientAccountBalanceException e) {
                throw new InsufficientAccountBalanceException();
            }
            
        } catch (CustomerNotFoundException e) {
            throw new CustomerNotFoundException();
        }
    }
   
    
    // Test harness
    public static void main(String args[]) {
        // Testing addCustomer()
        TollRoad testRoad = new TollRoad();        
        Car testCar1 = new Car("AA11 AAA", "Ford", 5);
        CustomerAccount testAcc1 = new CustomerAccount("Joe", "Bloggs", testCar1);
        
        System.out.println("customers size: " + testRoad.customers.size());
        testRoad.addCustomer(testAcc1);
        System.out.println("1 customer added, customers size: " + testRoad.customers.size());
        System.out.println("First name: " + testRoad.customers.get(0).getFirstName());
        System.out.println("Last name : " + testRoad.customers.get(0).getLastName());
        System.out.println("Reg num   : " + testRoad.customers.get(0).getRegNum());
        System.out.println("Balance   : " + testRoad.customers.get(0).getBalance());        
        
        // Testing findCustomer
        Van testVan1 = new Van("VA55 NAN", "Renault", 900);
        CustomerAccount testAcc2 = new CustomerAccount("James", "Gosling", testVan1);
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