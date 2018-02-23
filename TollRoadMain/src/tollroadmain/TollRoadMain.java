package tollroadmain;

import java.io.File;
import java.util.Scanner;

public class TollRoadMain {

    public static TollRoad initialiseTollRoadFromFile() {
        TollRoad tollRoad = new TollRoad();
        
        File inFile = new File("customerData.txt");

        try {
            Scanner customerScan = new Scanner(inFile); 
            customerScan.useDelimiter("#"); // Split customers by #
            
            // Loop while next customer exists
            while (customerScan.hasNext()) { 
                String[] splitLine = customerScan.next().split(",");
                
                Vehicle newVehicle;
                // Detect type of vehicle and make appropriate object
                if (splitLine[0].equals("Car")) {
                    newVehicle = new Car(splitLine[1], splitLine[4], 
                                         Integer.parseInt(splitLine[5]));
                }
                else if (splitLine[0].equals("Van")) {
                    newVehicle = new Van(splitLine[1], splitLine[4], 
                                         Integer.parseInt(splitLine[5]));
                }
                else {
                    newVehicle = new Truck(splitLine[1], splitLine[4], 
                                           Integer.parseInt(splitLine[5]));                    
                }
                
                // Create new CustomerAccount object with vehicle
                CustomerAccount newCustomer;
                newCustomer = new CustomerAccount(splitLine[2], splitLine[3],
                                                  Integer.parseInt(splitLine[6]),
                                                  newVehicle);
                
                // Check for/set discount
                if (splitLine[7].equals("STAFF"))
                    newCustomer.activateStaffDiscount();
                if (splitLine[7].equals("FRIENDS_AND_FAMILY"))
                    newCustomer.activateFriendsAndFamilyDiscount();
                
                // Add CustomerAccount to ArrayList
                tollRoad.addCustomer(newCustomer);
            }
            
        } catch (Exception e) {
            System.out.println("Error parsing file");
        }

        return tollRoad;
    }
    
    public static void main(String[] args) {        

    }
    
}
        
    

    
    

