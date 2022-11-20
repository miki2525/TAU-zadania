package org.tau;

public class BankAccountStub extends BankAccount{

    private final BankAccount bankAccountStub = new BankAccount("1234", "StubName", AccType.DEBIT, 1000L, "StubOwner");
    @Override
    public String showAccount() {
        return "Name of account holder: " + bankAccountStub.getName() +
                ", Account no.: " + bankAccountStub.getAccno() +
                ", Account type: " + bankAccountStub.getAcc_type()  +
                ", Balance: " + bankAccountStub.getBalance() +
                ", Owner: " + bankAccountStub.getOwner();
    }

    @Override
    public long deposit(long amount) {
        return bankAccountStub.getBalance() + amount;
    }

    @Override
    public long withdrawal(long amount) {
        return bankAccountStub.getBalance() - amount;
    }

    @Override
    public boolean search(String ac_no) {
        return true;
    }

    @Override
    public String getAccno() {
        return bankAccountStub.getAccno();
    }

    @Override
    public String getName() {
        return bankAccountStub.getName();
    }

    @Override
    public AccType getAcc_type() {
        return bankAccountStub.getAcc_type();
    }

    @Override
    public long getBalance() {
        return bankAccountStub.getBalance();
    }

    @Override
    public String getOwner() {
        return bankAccountStub.getOwner();
    }
}
