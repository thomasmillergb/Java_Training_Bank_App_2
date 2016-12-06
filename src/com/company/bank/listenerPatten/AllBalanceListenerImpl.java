package com.company.bank.listenerPatten;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by G710 on 06/12/2016.
 */
public class AllBalanceListenerImpl implements BalanceListener {
    private AtomicInteger totalBalance_ = new AtomicInteger(0);
    @Override
    public void update(BalanceEvent balanceEvent) {
        totalBalance_.getAndAdd(balanceEvent.getN());
    }

    public int getTotalBalance() {
        return totalBalance_.get();
    }
}
