package com.company.bank.account;

import com.company.bank.listenerPatten.BalanceListener;

/**
 * Created by G710 on 05/12/2016.
 */
public interface Account {
    void depsit(int amount);

    void withdraw(int amount);

    int getBalance();

    int getID();

    void addBalanceListener(BalanceListener bl);
}
