package org.craftsmanship.gre;

import java.util.Objects;

public class Amount {


    private int amount;

    public Amount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return this.amount == amount.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "org.craftsmanship.gre.Amount{" +
                "amount=" + amount +
                '}';
    }


    public Amount increase(Amount balance) {
        return new Amount(amount+balance.amount);
    }
}
