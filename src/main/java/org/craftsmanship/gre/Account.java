package org.craftsmanship.gre;

import java.util.Objects;

public class Account {

    private final OperationHistory operationHistory = new OperationHistory();
    private Amount balance;

    public Account(Amount balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return balance.equals(account.balance);
    }

    @Override
    public int hashCode() {

        return Objects.hash(balance);
    }

    public Amount deposit(Amount balance) {
        this.balance = balance.increase(this.balance);
        operationHistory.add(new Operation(balance));
        return this.balance;
    }

    public void print(Screen screen) {
        operationHistory.print(screen);
    }
}
