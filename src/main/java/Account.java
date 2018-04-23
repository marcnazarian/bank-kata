import java.time.Instant;
import java.util.List;

public class Account {

    private Amount balance;
    private OperationHistory operationHistory;

    Account() {
        this(new Amount());
    }

    Account(Amount amount) {
        this.balance = amount;
        this.operationHistory = new OperationHistory();
    }

    public Operation deposit(Amount amount) {
        this.balance.add(amount);
        Operation operation = new Operation(OperationType.OPERATION_TYPE_DEPOSIT, amount);
        operationHistory.add(operation);
        return operation;
    }

    public Operation withdraw(Amount amount) throws NotEnoughMoneyException {
        return withdraw(amount, OperationType.OPERATION_TYPE_WITHDRAW);
    }

    private Operation withdraw(Amount amount, OperationType operationType) throws NotEnoughMoneyException {
        if (amount.isGreaterOrEquals(this.balance)) {
            throw new NotEnoughMoneyException("Operation can't be done: not enough money on this account!");
        }
        this.balance.remove(amount);
        Operation operation = new Operation(operationType, amount);
        operationHistory.add(operation);
        return operation;
    }

    public Operation transfer(Account accountTo, Amount amount) throws NotEnoughMoneyException {
        Operation operation = withdraw(amount, OperationType.OPERATION_TYPE_TRANSFER);
        accountTo.deposit(amount);
        return operation;
    }

    public void printAllOperations(Screen screen) {
        operationHistory.printAllOperations(screen);
    }

    public void printCurrentState(Screen screen) {
        AccountState accountState = new AccountState(Instant.now(), balance);
        accountState.printCurrentState(screen);
    }

    public List<Operation> operationsFilteredBy(OperationType operationType) {
        return operationHistory.filteredBy(operationType);
    }

    public List<Operation> operationsFilteredBy(Amount amount) {
        return operationHistory.filteredBy(amount);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return balance != null ? balance.equals(account.balance) : account.balance == null;
    }

    @Override
    public int hashCode() {
        return balance != null ? balance.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Account{" +
                "amount=" + balance +
                '}';
    }


}
