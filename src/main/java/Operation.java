import java.time.Instant;

public class Operation {
    
    private Instant timestamp;
    private OperationAmount operationAmount;

    Operation(OperationType operationType, Amount amount) {
      this(Instant.now(), new OperationAmount(operationType, amount));
    }

    private Operation(Instant timestamp, OperationAmount operation) {
        this.timestamp = timestamp;
        this.operationAmount = operation;
    }

    public void print(Screen screen) {
        screen.writeOperation(this);
    }

    public boolean filterBy(OperationType operationType) {
        return this.operationAmount.filterBy(operationType);
    }

    public boolean filterBy(Amount amount) {
        return this.operationAmount.filterBy(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation that = (Operation) o;

        return (timestamp != null ? timestamp.equals(that.timestamp) : that.timestamp == null) && (operationAmount != null ? operationAmount.equals(that.operationAmount) : that.operationAmount == null);
    }

    @Override
    public int hashCode() {
        int result = timestamp != null ? timestamp.hashCode() : 0;
        result = 31 * result + (operationAmount != null ? operationAmount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "timestamp=" + timestamp +
                ", operationAmount=" + operationAmount +
                '}';
    }
}
