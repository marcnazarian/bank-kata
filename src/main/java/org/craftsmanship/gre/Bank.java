package org.craftsmanship.gre;

public class Bank {


    public Account createAccount() {
        return new Account(new Amount(0));

    }

    public Amount deposit(Account account, Amount balance) {
        return  account.deposit(balance);
    }

    public void print(Account account, Screen screen) {
        account.print(screen);
    }
}
