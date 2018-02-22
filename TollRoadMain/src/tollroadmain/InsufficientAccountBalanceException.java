package tollroadmain;

public class InsufficientAccountBalanceException extends Exception{
    
    public InsufficientAccountBalanceException() {
    }

    public String toString() {
        return "Error: Insufficient Account Balance";
    }
}
