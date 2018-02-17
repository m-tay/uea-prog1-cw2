package tollroadmain;

public class CustomerAccount { // TODO implement Comparable
    private enum Discount {NONE, STAFF, FRIENDS_AND_FAMILY}
    
    private String firstName;
    private String lastName;
    private int balance;
    private Discount discount;
    private Vehicle vehicle;
    
    // TODO implement constructor
    
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
    
}

