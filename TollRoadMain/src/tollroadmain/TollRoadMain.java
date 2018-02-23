package tollroadmain;

import java.io.File;
import java.util.Scanner;

public class TollRoadMain {

    public static TollRoad initialiseTollRoadFromFile() {
        TollRoad tollRoad = new TollRoad();
        
        File inFile = new File("customerData.txt");
        String[] line; 
        
        try {
            Scanner customerScan = new Scanner(inFile); 
            customerScan.useDelimiter("#"); // Split customers by #
            
            // Loop while next customer exists
            while (customerScan.hasNext()) { 
                
                // Split customer line into array on delimiter
                line = customerScan.next().split(","); 
                
                Vehicle newVehicle;
                // Detect type of vehicle and make appropriate object
                switch (line[0]) {
                    case "Car":
                        newVehicle = new Car(line[1], line[4],
                                             Integer.parseInt(line[5]));
                        break;
                    case "Van":
                        newVehicle = new Van(line[1], line[4],
                                             Integer.parseInt(line[5]));
                        break;
                    default:
                        newVehicle = new Truck(line[1], line[4],                    
                                               Integer.parseInt(line[5]));
                        break;
                }
                
                // Create new CustomerAccount object with vehicle
                CustomerAccount newCustomer;
                newCustomer = new CustomerAccount(line[2], line[3],
                                                  Integer.parseInt(line[6]),
                                                  newVehicle);
                
                // Check for/set discount
                if (line[7].equals("STAFF"))
                    newCustomer.activateStaffDiscount();
                if (line[7].equals("FRIENDS_AND_FAMILY"))
                    newCustomer.activateFriendsAndFamilyDiscount();
                
                // Add CustomerAccount to ArrayList
                tollRoad.addCustomer(newCustomer);
            }
            
        } catch (Exception e) {
            System.out.println("Error parsing file");
        }

        return tollRoad;
    }
   
    static void simulateFromFile(TollRoad road) {
        File inFile = new File("transactions.txt");
        String line[];
                       
        try {
            Scanner transScan = new Scanner(inFile);
            transScan.useDelimiter("\\$"); // Split transactions by
            
            // Loop while next transaction exists
            while (transScan.hasNext()) {
                
                // Split transaction line into array on delimiter
                line = transScan.next().split(",");
                
                // Add funds code
                if(line[0].equals("addFunds")) {
                    try {
                        // line[1] = regNum, line[2] = funds
                        road.findCustomer(line[1]).addFunds(Integer.parseInt(line[2]));
                        System.out.println(line[1] + ": " + line[2] + " added successfully");
                    } catch (CustomerNotFoundException e) {
                        System.out.println(line[1] + ": addFunds failed. CustomerAccount does not exist");
                    }
                }
                
                // Make trip code
                if(line[0].equals("makeTrip")) {
                    try {
                        // line[1] = regNum
                        road.chargeCustomer(line[1]);  
                        System.out.println(line[1] + ": Trip completed successfully. Total so far: " + road.getMoneyMade());
                    } catch (CustomerNotFoundException e) {
                        System.out.println(line[1] + ": makeTrip failed. CustomerAccountD does not exist");
                    } catch (InsufficientAccountBalanceException e) {
                        System.out.println(line[1] + ": makeTrip failed. Insufficient funds");
                    }
                }
            }            
        } catch (Exception e) {
            System.out.println("Error parsing file");
        }
    }
    
    public static void main(String[] args) {   

        TollRoad tollRoad = initialiseTollRoadFromFile();
        simulateFromFile(tollRoad);
        System.out.println("Simulation run");
        System.out.println("Money made: " + tollRoad.getMoneyMade());
        
    }
    
}
        
    

    
    

