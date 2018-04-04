package org.craftsmanship.gre;

import java.util.Objects;

public class Operation {
    private Amount amount;

    public Operation(Amount amount) {

        this.amount = amount;
    }

    @Override
    public String toString() {
        return "org.craftsmanship.gre.Operation{" +
                "amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(amount, operation.amount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(amount);
    }
}
