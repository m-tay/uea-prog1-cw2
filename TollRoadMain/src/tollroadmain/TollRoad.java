package tollroadmain;

import java.util.ArrayList;

public class TollRoad {
    private ArrayList<CustomerAccount> customers;
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
    
    
    // Test harness
  //  public static void main(String args[]) {
        
    }