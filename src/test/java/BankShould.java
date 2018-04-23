import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;

public class BankShould {

    private Account account;
    
    @Before
    public void setUp() {
        account = Bank.createNewAccount();
    }

    @Test
    public void create_account_with_no_money() {
        assertThat(account).isEqualTo(new Account(new Amount(0)));
    }
    
    @Test
    public void increase_account_after_deposit() {
        account.deposit(new Amount(20));
        
        assertThat(account).isEqualTo(new Account(new Amount(20)));
    }

    @Test
    public void increase_account_after_two_deposit() {
        account.deposit(new Amount(20));
        account.deposit(new Amount(10));

        assertThat(account).isEqualTo(new Account(new Amount(30)));
    }

    @Test
    public void decrease_account_after_withdraw() throws NotEnoughMoneyException {
        account.deposit(new Amount(200));
        account.withdraw(new Amount(40));

        assertThat(account).isEqualTo(new Account(new Amount(160)));
    }

    @Test
    public void not_allow_withdraw_when_not_enough_money() {
        account.deposit(new Amount(20));

        assertThatExceptionOfType(NotEnoughMoneyException.class).isThrownBy(() -> account.withdraw(new Amount(40)))
                    .withMessageContaining("not enough money")
                    .withNoCause();

        assertThat(account).isEqualTo(new Account(new Amount(20)));
    }

    @Test
    public void print_all_operations() throws NotEnoughMoneyException {
        Account account2 = Bank.createNewAccount();

        Operation firstOperation = account.deposit(new Amount(40));
        Operation secondOperation = account.deposit(new Amount(30));
        Operation thirdOperation = account.transfer(account2, new Amount(5));

        List<Operation> operations = new ArrayList<>();
        Screen screen = new Screen() {
            @Override
            public void writeOperation(Operation operation) {
                operations.add(operation);
            }

            @Override
            public void writeCurrentState(AccountState accountstate) {
                
            }
        };

        List<Operation> expectedOperations = Arrays.asList(firstOperation, secondOperation, thirdOperation);

        Bank.printAllOperations(account, screen);
        assertEquals(expectedOperations, operations);
    }

    @Test
    public void print_current_state_of_account() throws NotEnoughMoneyException {
        account.deposit(new Amount(40));
        account.deposit(new Amount(30));
        account.withdraw(new Amount(15));

        List<AccountState> currentAccountStates = new ArrayList<>();

        Screen screen = new Screen() {
            @Override
            public void writeOperation(Operation operation) {

            }

            @Override
            public void writeCurrentState(AccountState accountState) {
                currentAccountStates.add(accountState);
            }
        };

        AccountState expectedAccountState = new AccountState(Instant.now(), new Amount(55));

        Bank.printCurrentState(account, screen);
        assertEquals(expectedAccountState, currentAccountStates.get(0));
    }

    @Test
    public void transfer_some_money_between_accounts() throws NotEnoughMoneyException {
        Account accountFrom = Bank.createNewAccount();
        Account accountTo = Bank.createNewAccount();

        accountFrom.deposit(new Amount(200));
        accountTo.deposit(new Amount(30));
        accountFrom.transfer(accountTo, new Amount(150));

        assertThat(accountFrom).isEqualTo(new Account(new Amount(50)));
        assertThat(accountTo).isEqualTo(new Account(new Amount(180)));
    }

    @Test
    public void not_allow_transfer_when_not_enough_money() {
        Account accountFrom = Bank.createNewAccount();
        Account accountTo = Bank.createNewAccount();

        accountFrom.deposit(new Amount(200));
        accountTo.deposit(new Amount(30));

        assertThatExceptionOfType(NotEnoughMoneyException.class).isThrownBy(() -> accountFrom.transfer(accountTo, new Amount(250)))
                .withMessageContaining("not enough money")
                .withNoCause();

        assertThat(accountFrom).isEqualTo(new Account(new Amount(200)));
        assertThat(accountTo).isEqualTo(new Account(new Amount(30)));
    }

    @Test
    public void filter_account_operations_by_type() throws NotEnoughMoneyException {
        // arrange
        Account account2 = Bank.createNewAccount();
        Account account3 = Bank.createNewAccount();

        Operation depositOperation1 = account.deposit(new Amount(40));
        Operation depositOperation2 = account.deposit(new Amount(30));
        Operation transferOperation1 = account.transfer(account2, new Amount(5));
        Operation withdrawOperation1 = account.withdraw(new Amount(15));
        Operation depositOperation3 = account.deposit(new Amount(25));
        Operation transferOperation2 = account.transfer(account3, new Amount(5));


        // act
        List<Operation> depositOperationsForAccount1 = account.operationsFilteredBy(OperationType.OPERATION_TYPE_DEPOSIT);
        List<Operation> withDrawOperationsForAccount1 = account.operationsFilteredBy(OperationType.OPERATION_TYPE_WITHDRAW);
        List<Operation> transferOperationsForAccount1 = account.operationsFilteredBy(OperationType.OPERATION_TYPE_TRANSFER);

        // assert
        List<Operation> expectedDepositOperationsForAccount1 = Arrays.asList(depositOperation1, depositOperation2, depositOperation3);
        List<Operation> expectedTransferOperationsForAccount1 = Arrays.asList(transferOperation1, transferOperation2);
        List<Operation> expectedWithDrawOperationsForAccount1 = Collections.singletonList(withdrawOperation1);
                
        assertEquals(expectedDepositOperationsForAccount1, depositOperationsForAccount1);
        assertEquals(expectedTransferOperationsForAccount1, transferOperationsForAccount1);
        assertEquals(expectedWithDrawOperationsForAccount1, withDrawOperationsForAccount1);
    }

    @Test
    public void filter_account_operations_by_amount() throws NotEnoughMoneyException {
        // arrange
        Account account2 = Bank.createNewAccount();

        account.deposit(new Amount(40));
        Operation depositOperation2 = account.deposit(new Amount(5));
        account.withdraw(new Amount(15));
        Operation transferOperation1 = account.transfer(account2, new Amount(5));
        account.deposit(new Amount(25));
        Operation withdrawOperation2 = account.withdraw(new Amount(5));

        // act
        List<Operation> operationsWithAmount5 = account.operationsFilteredBy(new Amount(5));

        // assert
        List<Operation> expectedOperationsWithAmount5 = Arrays.asList(depositOperation2, transferOperation1, withdrawOperation2);

        assertEquals(expectedOperationsWithAmount5, operationsWithAmount5);
    }
}
