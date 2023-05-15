package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Collection<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Account> findById(Long cbu) {
        return accountRepository.findById(cbu);
    }

    public Collection<Transaction> findTransactionByCbu(Long cbu) {
        Collection<Transaction> transactions = getTransactions();
        transactions.removeIf(t -> t.getTransactionCbu() != cbu);
        return transactions;
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void deleteById(Long cbu) {
        accountRepository.deleteById(cbu);
    }

    public void deleteByTransactionId(Long id) {
        transactionRepository.deleteById(id);
    }

    @Transactional
    public Account withdraw(Long cbu, Double sum) {
        Account account = accountRepository.findAccountByCbu(cbu);

        if (account.getBalance() < sum) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - sum);
        accountRepository.save(account);

        return account;
    }

    @Transactional
    public Account deposit(Long cbu, Double sum) {

        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }
        Account account = accountRepository.findAccountByCbu(cbu);
        if (sum < 2000){
            account.setBalance(account.getBalance() + sum);
        } else if (sum > 5000){
            account.setBalance(account.getBalance() + (double) 500 + sum);
        } else {
            account.setBalance(account.getBalance() + sum*1.1);
        }
        accountRepository.save(account);

        return account;
    }

}
