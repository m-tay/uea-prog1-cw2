package tollroadmain;

public class CustomerNotFoundException extends Exception {
        
    public CustomerNotFoundException() {
        
    }
    
    public String toString() {
        return "Error: Customer not found";
    }
    
    
}
