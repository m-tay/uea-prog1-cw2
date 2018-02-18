package tollroadmain;

public class InsufficientAccountBalanceException extends Exception{
    private final int accountBalance;
    private final int tripCost;
    
    public InsufficientAccountBalanceException(int accountBalance, int tripCost) {
        this.accountBalance = accountBalance;
        this.tripCost = tripCost;
    }
    
    public int getAccountBalance() {
        return accountBalance;
    }
    
    public int getTripCost() {
        return tripCost;
    }
}
