public class Amount {

    private static final int ZERO = 0;
    
    private int value;

    Amount() {
        this(ZERO);
    }

    Amount(int value) {
        this.value = value;
    }

    public void add(Amount amount) {
        this.value += amount.value;
    }

    public void remove(Amount amount) {
        this.value -= amount.value;
    }
    
    public boolean isGreaterOrEquals(Amount amount) {
        return this.value >= amount.value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Amount amount = (Amount) o;

        return value == amount.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "value=" + value +
                '}';
    }

}
