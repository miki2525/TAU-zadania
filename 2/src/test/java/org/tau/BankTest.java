package org.tau;

import org.junit.jupiter.api.BeforeEach;


import static org.junit.jupiter.api.Assertions.*;

class Test {

    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank("1234", "SuperAccount", AccType.DEBIT, 0L);
        bank.deposit(1000L);
    }

    @org.junit.jupiter.api.Test
    void shouldShowAccount() {
        assertEquals(bank.showAccount(), "Name of account holder: SuperAccount" +
                ", Account no.: 1234" +
                ", Account type: DEBIT"  +
                ", Balance: 1000");
    }

    @org.junit.jupiter.api.Test
    void shouldDepositAndIncreaseBalance() {
        bank.deposit(1000L);
        assertEquals(bank.getBalance(),2000L);
    }

    @org.junit.jupiter.api.Test
    void shouldThrowIllegalArgumentExceptionDuringDeposit() {
        assertThrows(IllegalArgumentException.class, () -> bank.deposit(-10000));
    }

    @org.junit.jupiter.api.Test
    void shouldMakeAWithdrawalAndDecreaseBalance() {
        bank.withdrawal(500L);
        assertEquals(bank.getBalance(), 500L);
    }

    @org.junit.jupiter.api.Test
    void shouldThrowBalanceIsLessThanAmountDuringWithrawal() {
        IllegalStateException ex = assertThrows(IllegalStateException.class, ()->bank.withdrawal(30000L));
        assertEquals(ex.getMessage(), "balance is less than amount. Transaction failed");
    }

    @org.junit.jupiter.api.Test
    void shouldThrowAmountIsTooSmallDuringWithrawal() {
        IllegalStateException ex = assertThrows(IllegalStateException.class, ()->bank.withdrawal(-30000L));
        assertEquals(ex.getMessage(), "amount is too small. Transaction failed");
    }

    @org.junit.jupiter.api.Test
    void shouldFindBankAccount() {
        String accountNumber = "1234";
        assertTrue(bank.search(accountNumber));
    }
}
