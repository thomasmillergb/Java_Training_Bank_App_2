package com.company.bank;

import com.company.bank.listenerPatten.BalanceEvent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by G710 on 06/12/2016.
 */
public class TotalBalanceWrapper {
    private AtomicInteger totalBalance_ = new AtomicInteger(0);

    public void update(BalanceEvent balanceEvent) {
        totalBalance_.getAndAdd(balanceEvent.getN());
    }

    public int getTotalBalance() {
        return totalBalance_.get();
    }
}
