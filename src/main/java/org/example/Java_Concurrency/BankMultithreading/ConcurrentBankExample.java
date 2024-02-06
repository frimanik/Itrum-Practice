package org.example.Java_Concurrency.BankMultithreading;


public class ConcurrentBankExample {
    public static void main(String[] args) {
        ConcurrentBank bank = new ConcurrentBank();

        // Создание счетов
        BankAccount account1 = bank.createAccount(1000);
        BankAccount account2 = bank.createAccount(500);

        // Перевод между счетами
        Thread transferThread1 = new Thread(() -> bank.transfer(account1.getAccountId(), account2.getAccountId(), 200));
        Thread transferThread2 = new Thread(() -> bank.transfer(account2.getAccountId(), account1.getAccountId(), 100));

        transferThread1.start();
        transferThread2.start();


        try {
            transferThread1.join();
            System.out.println("acc1:"+account1.getBalance());
            System.out.println("acc2:"+account2.getBalance());
            transferThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Вывод общего баланса
        System.out.println("Total balance: " + bank.getTotalBalance());
    }
}
