package org.craftsmanship.gre;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BankShould {
    @Test
    public void createAccountWithZeroBalance() {
        Bank bank = new Bank();

        // Test
        Account account = bank.createAccount();

        assertEquals(new Account(new Amount(0)), account);
    }

    @Test
    public void increaseAccountAfterDeposit() {
        // Préparation
        Bank bank = new Bank();
        Account account = bank.createAccount();

        // Test

        Amount balance = bank.deposit(account, new Amount(40));

        // Vérifications
        assertEquals(new Amount(40), balance);
    }

    @Test
    public void increaseAccountAfterTwoDeposit() {
        // Pr
        // éparation
        Bank bank = new Bank();
        Account account = bank.createAccount();

        // Test

        Amount balance = bank.deposit(account, new Amount(40));
        Amount balanceTwo = bank.deposit(account, new Amount(30));

        // Vérifications
        assertEquals(new Amount(70), balanceTwo);
    }

    @Test
    public void printAllOperations() {
        Bank bank = new Bank();
        Account account = bank.createAccount();

        // Test

        Amount balance = bank.deposit(account, new Amount(40));
        Amount balanceTwo = bank.deposit(account, new Amount(30));

        List<Operation> arguments = new ArrayList<>();
        Screen screen = new Screen() {
            @Override
            public void writeOperation(Operation operation) {
                arguments.add(operation);
            }
        };

        Operation secondOperation = new Operation(new Amount(30));
        Operation firstOperation = new Operation(new Amount(40));
        List<Operation> expectedOperations = Arrays.asList(firstOperation, secondOperation);

        bank.print(account, screen);
        assertEquals(expectedOperations, arguments);

    }
}
