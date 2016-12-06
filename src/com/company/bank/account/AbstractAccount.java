package com.company.bank.account;

import com.company.bank.monoStatePatten.TotalBalanceMonoState;
import com.company.bank.listenerPatten.BalanceEvent;
import com.company.bank.listenerPatten.BalanceListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by G710 on 06/12/2016.
 */
public abstract class AbstractAccount  {

    final Object sysObj_ = new Object();
    protected int id_;
    protected int balance_;
    protected TotalBalanceMonoState totalBalanceMonoState_;
    protected List<BalanceListener> balanceListeners_ = new ArrayList<>();

    public AbstractAccount(int id, TotalBalanceMonoState totalBalanceMonoState) {
        balance_ = 0;
        id_ = id;
        totalBalanceMonoState_ = totalBalanceMonoState;
    }

    public void updateListener (int amount){
        BalanceEvent balanceEvent = new BalanceEvent(amount);
        balanceListeners_.forEach(balanceListener -> balanceListener.update(balanceEvent));
    }
    public void addBalanceListener(BalanceListener bl) {
        bl.update(new BalanceEvent(balance_));
        balanceListeners_.add(bl);
    }

    public int getBalance() {
        return balance_;
    }

    public int getID() {
        return id_;
    }


    public TotalBalanceMonoState getTotalBalanceMonoState() {
        return totalBalanceMonoState_;
    }


    public List<BalanceListener> getBalanceListeners() {
        return balanceListeners_;
    }


    public Object getSysObj() {
        return sysObj_;
    }
}
