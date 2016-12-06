package com.company.bank.account;

import com.company.bank.monoStatePatten.TotalBalanceMonoState;

/**
 * Created by G710 on 05/12/2016.
 */
public class CurrentAccount extends AbstractAccount implements Account {



    public CurrentAccount(int id, TotalBalanceMonoState totalBalanceMonoState) {
        super(id, totalBalanceMonoState);
    }

    @Override
    public synchronized void depsit(int amount) {
        synchronized (sysObj_) {
            balance_+= amount;
            updateListener(amount);
            getTotalBalanceMonoState().updateTotalBalance(amount);
        }
    }

    @Override
    public synchronized void withdraw(int amount) {
        synchronized (sysObj_) {
            balance_-= amount;
            updateListener(-amount);
            getTotalBalanceMonoState().updateTotalBalance(-amount);
        }
    }


}
