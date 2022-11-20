package org.tau;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankAccountTest {

    @Mock
    BankAccount bankAccount;

    @Test
    void shouldShowAccount() {
        //
        when(bankAccount.showAccount()).thenReturn("Name of account holder: SuperAccount" +
                ", Account no.: 1234" +
                ", Account type: DEBIT" +
                ", Balance: 1000" +
                ", Owner: John");
        //
        //
        assertEquals(bankAccount.showAccount(), "Name of account holder: SuperAccount" +
                ", Account no.: 1234" +
                ", Account type: DEBIT"  +
                ", Balance: 1000" +
                ", Owner: John");
    }

    @Test
    void shouldShowBalance() {
        //
        long expectedValue = 2000L;
        when(bankAccount.getBalance()).thenReturn(2000L);
        //
        //
        assertEquals(expectedValue,bankAccount.getBalance());
    }

    @Test
    void shouldMakeADeposit() {
        //
        final long deposit = 500L;
        final long expectedBalance = 1500L;
        when(bankAccount.getBalance()).thenReturn(1500L);
        when(bankAccount.deposit(deposit)).thenReturn(1500L);
        //
        long balanceAfterDeopsit= bankAccount.deposit(deposit);
        //
        assertEquals(expectedBalance, balanceAfterDeopsit);
        assertEquals(balanceAfterDeopsit, bankAccount.getBalance());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionDuringDeposit() {
        //
        when(bankAccount.deposit(anyLong())).thenThrow(IllegalArgumentException.class);
        //
        //
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-10000));
    }

    @Test
    void shouldMakeAWithdrawal() {
        //
        final long withdrawal = 500L;
        final long expectedBalance = 1500L;
        when(bankAccount.getBalance()).thenReturn(1500L);
        when(bankAccount.withdrawal(withdrawal)).thenReturn(1500L);
        //
        long balanceAfterWitdrawal = bankAccount.withdrawal(withdrawal);
        //
        assertEquals(expectedBalance, balanceAfterWitdrawal);
        assertEquals(balanceAfterWitdrawal, bankAccount.getBalance());
    }

    @Test
    void shouldThrowBalanceIsLessThanAmountDuringWithrawal() {
        //
        final long veryBigLong = 9999999999L;
        when(bankAccount.withdrawal(veryBigLong)).thenThrow(new IllegalStateException("balance is less than amount. Transaction failed"));
        //
        IllegalStateException ex = assertThrows(IllegalStateException.class, ()-> bankAccount.withdrawal(veryBigLong));
        //
        assertEquals(ex.getMessage(), "balance is less than amount. Transaction failed");
    }

    @Test
    void shouldThrowAmountIsTooSmallDuringWithrawal() {
        //
        final long toSmallAmount = 0L;
        when(bankAccount.withdrawal(toSmallAmount)).thenThrow(new IllegalStateException("amount is too small. Transaction failed"));
        //
        IllegalStateException ex = assertThrows(IllegalStateException.class, ()-> bankAccount.withdrawal(toSmallAmount));
        //
        assertEquals(ex.getMessage(), "amount is too small. Transaction failed");
    }

    @Test
    void shouldFindBankAccount() {
        //
        when(bankAccount.search(anyString())).thenReturn(true);
        //
        //
        assertTrue(bankAccount.search("1234"));
    }
}
