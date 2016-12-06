package com.company.bank.monoStatePatten;

/**
 * Created by G710 on 05/12/2016.
 */
public interface TotalBalanceMonoState {
    int getTotalBalance();
    void updateTotalBalance(int amount);
}
