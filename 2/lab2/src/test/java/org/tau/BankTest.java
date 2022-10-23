package org.tau;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    Bank bank;

    @BeforeEach
    void setup(){

        BankAccount bankAccount1 = new BankAccount("1234", "SuperAccount", AccType.DEBIT, 0L, "John");
        BankAccount bankAccount2 = new BankAccount("5678", "CreditAccount", AccType.CREDIT, 10000L, "Mike");

        bank = new Bank(Arrays.asList(bankAccount1, bankAccount2));
    }

    @Test
    void shouldfindAccount() {
        Optional<BankAccount> accountFound = bank.findAccount("1234");
       Assertions.assertTrue(accountFound.isPresent());
    }

    @Test
    void shouldNotFindAccount() {
        Optional<BankAccount> accountFound = bank.findAccount("9101112");
        Assertions.assertTrue(accountFound.isEmpty());
    }
}