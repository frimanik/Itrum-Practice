package org.example.Java_Concurrency.BankMultithreading;

import java.util.ArrayList;
import java.util.UUID;

public class ConcurrentBank {
    static ArrayList<BankAccount> accounts;

    public ConcurrentBank() {
        accounts = new ArrayList<BankAccount>();
    }

    public void transfer(UUID fromAccountId, UUID toAccountId, int amount) {
        BankAccount fromAccount = getAccountById(fromAccountId);
        BankAccount toAccount = getAccountById(toAccountId);

        // Ensure a consistent order to avoid deadlock
        BankAccount firstAccount = (fromAccountId.compareTo(toAccountId) < 0) ? fromAccount : toAccount;
        BankAccount secondAccount = (fromAccountId.compareTo(toAccountId) < 0) ? toAccount : fromAccount;

        firstAccount.lock.lock();
        try {
            secondAccount.lock.lock();
            try {
                if (fromAccount.getBalance() >= amount) {
                    fromAccount.withdraw(amount);
                    toAccount.deposit(amount);
                } else {
                    System.out.println("Insufficient funds for transfer from account " + fromAccountId);
                }
            } finally {
                secondAccount.lock.unlock();
            }
        } finally {
            firstAccount.lock.unlock();
        }
    }

    private BankAccount getAccountById(UUID accountId) {
        for (BankAccount account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        throw new IllegalArgumentException("Account not found with ID: " + accountId);
    }

    public int getTotalBalance() {
        int totalBalance = 0;
        for (BankAccount account : accounts) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    public BankAccount createAccount(int balance) {
        UUID accountId = UUID.randomUUID();
        BankAccount newAccount = new BankAccount(accountId, balance);
        accounts.add(newAccount);
        return newAccount;
    }
}
