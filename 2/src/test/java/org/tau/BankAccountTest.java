package org.tau;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        bankAccount = new BankAccount("1234", "SuperAccount", AccType.DEBIT, 0L, "John");
        bankAccount.deposit(1000L);
    }

    @Test
    void shouldShowAccount() {
        assertEquals(bankAccount.showAccount(), "Name of account holder: SuperAccount" +
                ", Account no.: 1234" +
                ", Account type: DEBIT"  +
                ", Balance: 1000" +
                ", Owner: John");
    }

    @Test
    void shouldDepositAndIncreaseBalance() {
        bankAccount.deposit(1000L);
        assertEquals(bankAccount.getBalance(),2000L);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionDuringDeposit() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-10000));
    }

    @Test
    void shouldMakeAWithdrawalAndDecreaseBalance() {
        bankAccount.withdrawal(500L);
        assertEquals(bankAccount.getBalance(), 500L);
    }

    @Test
    void shouldThrowBalanceIsLessThanAmountDuringWithrawal() {
        IllegalStateException ex = Assertions.assertThrows(IllegalStateException.class, ()-> bankAccount.withdrawal(30000L));
        Assertions.assertEquals(ex.getMessage(), "balance is less than amount. Transaction failed");
    }

    @Test
    void shouldThrowAmountIsTooSmallDuringWithrawal() {
        IllegalStateException ex = Assertions.assertThrows(IllegalStateException.class, ()-> bankAccount.withdrawal(-30000L));
        Assertions.assertEquals(ex.getMessage(), "amount is too small. Transaction failed");
    }

    @Test
    void shouldFindBankAccount() {
        String accountNumber = "1234";
        assertTrue(bankAccount.doesExist(accountNumber));
    }
}
