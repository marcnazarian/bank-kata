import java.time.Instant;

public class AccountState {
    private final Instant timestamp;
    private final Amount balance;

    public AccountState(Instant timestamp, Amount balance) {

        this.timestamp = timestamp;
        this.balance = balance;
    }

    public void printCurrentState(Screen screen) {
        screen.writeCurrentState(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountState that = (AccountState) o;

        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        return balance != null ? balance.equals(that.balance) : that.balance == null;
    }

    @Override
    public int hashCode() {
        int result = timestamp != null ? timestamp.hashCode() : 0;
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccountState{" +
                "timestamp=" + timestamp +
                ", balance=" + balance +
                '}';
    }
}
