package com.company.bank.monoStatePatten;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by G710 on 05/12/2016.
 */
public class TotalBalanceMonoStateImpl implements TotalBalanceMonoState {

    private static AtomicInteger totalBalance_ = new AtomicInteger(0);
    @Override
    public int getTotalBalance() {
        return totalBalance_.get();
    }

    @Override
    public void updateTotalBalance(int amount) {

        totalBalance_.getAndAdd(amount);
    }
}
