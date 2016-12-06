package com.company.bank.listenerPatten;

import com.company.bank.TotalBalanceWrapper;

/**
 * Created by G710 on 06/12/2016.
 */
public class AllBalanceListenerImpl implements BalanceListener {
    private TotalBalanceWrapper totalBalance_ = new TotalBalanceWrapper();
    @Override
    public void update(BalanceEvent balanceEvent) {
        totalBalance_.update(balanceEvent);
    }

    public int getTotalBalance() {
        return totalBalance_.getTotalBalance();
    }
}
