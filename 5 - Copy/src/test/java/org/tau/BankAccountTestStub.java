package org.tau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountTestStub {

    BankAccountStub bankAccountStub;

    @BeforeEach
    void setup(){
        bankAccountStub = new BankAccountStub();
    }

    @Test
    void shouldShowAccount() {
        //
        assertEquals("Name of account holder: StubName" +
                ", Account no.: 1234" +
                ", Account type: DEBIT" +
                ", Balance: 1000" +
                ", Owner: StubOwner", bankAccountStub.showAccount());
    }

    @Test
    void shouldShowBalance() {
        long expectedValue = 1000L;
        assertEquals(expectedValue,bankAccountStub.getBalance());
    }

    @Test
    void shouldMakeADeposit() {
        final long deposit = 500L;
        final long expectedBalance = 1500L;
        long balanceAfterDeopsit= bankAccountStub.deposit(deposit);
        //
        assertEquals(expectedBalance, balanceAfterDeopsit);
    }

    @Test
    void shouldMakeAWithdrawal() {
        //
        final long withdrawal = 500L;
        final long expectedBalance = 500L;
        long balanceAfterWitdrawal = bankAccountStub.withdrawal(withdrawal);
        assertEquals(expectedBalance, balanceAfterWitdrawal);
    }

    @Test
    void shouldFindBankAccount() {
        assertTrue(bankAccountStub.search("1234"));
    }
}
