package com.company.bank;


import com.company.bank.account.Account;
import com.company.bank.account.CurrentAccount;
import com.company.bank.account.SavingAccount;
import com.company.bank.listenerPatten.AllBalanceListenerImpl;
import com.company.bank.listenerPatten.BalanceListener;
import com.company.bank.monoStatePatten.TotalBalanceMonoState;
import com.company.bank.monoStatePatten.TotalBalanceMonoStateImpl;
import com.company.bank.teller.Teller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by G710 on 05/12/2016.
 */
public class Bank {


    public static void main(String[] args) {
        List<Account> accounts = new LinkedList<>();
        TotalBalanceMonoState totalBalanceMonoState = new TotalBalanceMonoStateImpl();

        BalanceListener allBalanceListener = new AllBalanceListenerImpl();
        BalanceListener savingBalanceListener = new AllBalanceListenerImpl();
        BalanceListener currentBalanceListener = new AllBalanceListenerImpl();
        createAccounts(accounts, totalBalanceMonoState, allBalanceListener, savingBalanceListener, currentBalanceListener);
        List<Teller> tellers = new LinkedList<>();

        performTransation(accounts, tellers);

        printSummary(accounts, totalBalanceMonoState, allBalanceListener, savingBalanceListener, currentBalanceListener);


    }

    private static void performTransation(List<Account> accounts, List<Teller> tellers) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        IntStream.range(0, 100).forEach(a -> tellers.add(new Teller(accounts)));
        tellers.forEach(executorService::submit);
        executorService.shutdown();
        try {
            while (!executorService.awaitTermination(400, TimeUnit.MILLISECONDS)) {
                System.out.println("not finished");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printSummary(List<Account> accounts, TotalBalanceMonoState totalBalanceMonoState, BalanceListener allBalanceListener, BalanceListener savingBalanceListener, BalanceListener currentBalanceListener) {
        accounts.forEach(account -> System.out.println("Account id : " + account.getID() + " Account Balance : " + account.getBalance()));
        System.out.println("Total Balance Mono Sate: " + totalBalanceMonoState.getTotalBalance());
        System.out.println("Total Balance Listener: " + allBalanceListener.getTotalBalance());
        System.out.println("Total Balance Saving Listener: " + savingBalanceListener.getTotalBalance());
        System.out.println("Total Balance Current Listener: " + currentBalanceListener.getTotalBalance());
    }

    private static void createAccounts(List<Account> accounts, TotalBalanceMonoState totalBalanceMonoState, BalanceListener allBalanceListener, BalanceListener savingBalanceListener, BalanceListener currentBalanceListener) {
        IntStream.range(0, 10).forEach(a -> {
            Account savingAccount = new CurrentAccount(a, totalBalanceMonoState);
            savingAccount.addBalanceListener(allBalanceListener);
            savingAccount.addBalanceListener(currentBalanceListener);
            accounts.add(savingAccount);

        });
        IntStream.range(11, 20).forEach(a -> {
            Account savingAccount = new SavingAccount(a, totalBalanceMonoState);
            savingAccount.addBalanceListener(allBalanceListener);
            savingAccount.addBalanceListener(savingBalanceListener);
            accounts.add(savingAccount);

        });
    }

    //Impl 1
//    public static void main(String[] args) {
//        List<Account> accounts = new LinkedList<>();
//        TotalBalanceMonoState totalBalanceMonoState = new TotalBalanceMonoStateImpl();
//        IntStream.range(0, 100).forEach(a -> accounts.add(new CurrentAccount(a, totalBalanceMonoState)));
//        List<Teller> teller = new LinkedList<>();
//        teller.add(new Teller(accounts));
//        teller.add(new Teller(accounts));
//        teller.add(new Teller(accounts));
//        teller.add(new Teller(accounts));
//
//        teller.parallelStream()
//                .forEach(t -> {
//                    try {
//                        t.work();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                });

//        IntStream.range(0, 100).forEach(a -> {
//            try {
//                teller.work(accounts);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//    }
}
