package com.company.bank.listenerPatten;

/**
 * Created by G710 on 06/12/2016.
 */
public interface BalanceListener {
    void update(BalanceEvent balanceEvent);

    int getTotalBalance();
}
