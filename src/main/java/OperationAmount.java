public class OperationAmount {
    
    private OperationType operationType;
    private Amount amount;

    OperationAmount(OperationType operationType, Amount amount) {
        this.operationType = operationType;
        this.amount = amount;
    }

    public boolean filterBy(OperationType operationType) {
        return this.operationType == operationType;
    }

    public boolean filterBy(Amount amount) {
        return this.amount.equals(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationAmount that = (OperationAmount) o;

        return operationType == that.operationType && (amount != null ? amount.equals(that.amount) : that.amount == null);
    }

    @Override
    public int hashCode() {
        int result = operationType != null ? operationType.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OperationAmount{" +
                "operationType=" + operationType +
                ", amount=" + amount +
                '}';
    }
}