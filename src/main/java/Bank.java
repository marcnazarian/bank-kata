public class Bank {
    
    public static Account createNewAccount() {
        return new Account();
    }

    public static void printAllOperations(Account account, Screen screen) {
        account.printAllOperations(screen);
    }

    public static void printCurrentState(Account account, Screen screen) {
        account.printCurrentState(screen);
    }
}
