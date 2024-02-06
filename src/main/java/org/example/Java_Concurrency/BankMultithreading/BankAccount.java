package org.example.Java_Concurrency.BankMultithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import java.util.UUID;

public class BankAccount {
    private final UUID accountId;
    private volatile int balance;
    final Lock lock = new ReentrantLock();

    public BankAccount( UUID id,int initialBalance) {
        this.accountId = id;
        this.balance = initialBalance;
    }

    public void deposit(int amount) {
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(int amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
            } else {
                System.out.println("Insufficient funds for withdrawal from account " + accountId);
            }
        } finally {
            lock.unlock();
        }
    }

    public int getBalance() {
        return balance;
    }

    public UUID getAccountId() {
        return accountId;
    }
}
