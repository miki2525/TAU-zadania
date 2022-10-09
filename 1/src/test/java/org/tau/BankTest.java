package org.tau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank("1234", "SuperAccount", AccType.DEBIT, 0L);
        bank.deposit(1000L);
    }

    @Test
    void shouldShowAccount() {
        assertEquals(bank.showAccount(), "Name of account holder: SuperAccount" +
                ", Account no.: 1234" +
                ", Account type: DEBIT"  +
                ", Balance: 1000");
    }

    @Test
    void shouldDepositAndIncreaseBalance() {
        bank.deposit(1000L);
        assertEquals(bank.getBalance(),2000L);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionDuringDeposit() {
        assertThrows(IllegalArgumentException.class, () -> bank.deposit(-10000));
    }

    @Test
    void shouldMakeAWithdrawalAndDecreaseBalance() {
        bank.withdrawal(500L);
        assertEquals(bank.getBalance(), 500L);
    }

    @Test
    void shouldThrowBalanceIsLessThanAmountDuringWithrawal() {
        IllegalStateException ex = assertThrows(IllegalStateException.class, ()->bank.withdrawal(30000L));
        assertEquals(ex.getMessage(), "balance is less than amount. Transaction failed");
    }

    @Test
    void shouldThrowAmountIsTooSmallDuringWithrawal() {
        IllegalStateException ex = assertThrows(IllegalStateException.class, ()->bank.withdrawal(-30000L));
        assertEquals(ex.getMessage(), "amount is too small. Transaction failed");
    }

    @Test
    void shouldSearchBank() {
        assertTrue(bank.search("1234"));
    }
}