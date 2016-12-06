package com.company.bank.CommandPattern;

import com.company.bank.account.Account;

/**
 * Created by G710 on 06/12/2016.
 */
public class WidthdrawCommand implements TransationCommand {
    Account account_;
    int amount_;

    public WidthdrawCommand(Account account, int amount) {
        account_ = account;
        amount_ = amount;
    }

    @Override
    public void execute() {
        account_.withdraw(amount_);
    }
}
