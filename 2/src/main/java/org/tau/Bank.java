package org.tau;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bank {

    private List<BankAccount> bankAccountList;

    public Bank() {
        this.bankAccountList = new ArrayList<>();
    }

    public Bank(List<BankAccount> account) {
        this.bankAccountList = account;
    }

    public Optional<BankAccount> findAccount(String acc_num) {
        return bankAccountList.stream().filter(bankAccount -> bankAccount.getAccno().equals(acc_num)).findFirst();
    }

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public void setBankAccountList(List<BankAccount> bankAccountList) {
        this.bankAccountList = bankAccountList;
    }
}
