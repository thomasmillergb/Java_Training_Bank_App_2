package com.company.bank.teller;

import com.company.bank.account.Account;
import com.company.bank.transationPattern.DepositCommand;
import com.company.bank.transationPattern.TransationCommand;
import com.company.bank.transationPattern.WidthdrawCommand;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Created by G710 on 05/12/2016.
 */
public class Teller implements Runnable {

    List<Account> accounts_;
    Queue<TransationCommand> transationCommands_;

    public Teller(List<Account> accounts) {
        accounts_ = accounts;
        transationCommands_ = new ArrayDeque<>();
    }

    public void work() throws InterruptedException {

        for (Account account : accounts_) {
            Thread.sleep(5L);
//            account.withdraw(10);
//            account.depsit(20);
            this.submitTransation(new WidthdrawCommand(account, 10));
            this.submitTransation(new DepositCommand(account, 20));
//            int amount = (int) (Math.random() * 100);
//            int amount = 10;

//            if (Math.random() > 0.50) {
//
//                account.depsit(amount);
//            } else {
//                account.withdraw(amount);
//            }
//            System.out.println("AccID: " + account.getID() + "  :  " + account.getBalance());
        }
    }

    private void submitTransation(TransationCommand transationCommand) {
        transationCommand.execute();
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
