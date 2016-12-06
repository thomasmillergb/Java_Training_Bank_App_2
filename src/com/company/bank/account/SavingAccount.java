package com.company.bank.account;

import com.company.bank.monoStatePatten.TotalBalanceMonoState;

/**
 * Created by G710 on 05/12/2016.
 */
public class SavingAccount extends AbstractAccount implements Account {

    private int PENALTY = 5;
    Object sysObj_ = new Object();

    public SavingAccount(int id, TotalBalanceMonoState totalBalanceMonoState) {
        super(id, totalBalanceMonoState);
    }

    @Override
    public synchronized void depsit(int amount) {
        synchronized (sysObj_) {
            balance_+= amount;
            updateListener(+amount);
            getTotalBalanceMonoState().updateTotalBalance(amount);
        }
    }

    @Override
    public synchronized void withdraw(int amount) {
        synchronized (sysObj_) {
            amount += PENALTY;
            balance_-= amount;
            updateListener(-amount);
            getTotalBalanceMonoState().updateTotalBalance(-1 * amount);
        }
    }
}
