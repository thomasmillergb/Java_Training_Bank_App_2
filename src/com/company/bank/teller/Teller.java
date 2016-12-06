package com.company.bank.teller;

import com.company.bank.account.*;

import java.util.List;

/**
 * Created by G710 on 05/12/2016.
 */
public class Teller implements Runnable{

    List<Account> accounts_;

    public Teller(List<Account> accounts) {
        accounts_ = accounts;
    }

    public void work() throws InterruptedException {

        for (Account account : accounts_) {
//            int amount = (int) (Math.random() * 100);
//            int amount = 10;
            Thread.sleep(5L);
            account.withdraw(10);
            account.depsit(20);
//            if (Math.random() > 0.50) {
//
//                account.depsit(amount);
//            } else {
//                account.withdraw(amount);
//            }
//            System.out.println("AccID: " + account.getID() + "  :  " + account.getBalance());
        }
    }

    @Override
    public void run() {
        try {
            work();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
