package com.marflo.ms.accountservice.service.endpoint;

import com.marflo.ms.accountservice.datamodel.dao.AccountRepository;
import com.marflo.ms.accountservice.datamodel.entity.Account;
import com.marflo.ms.accountservice.datamodel.exception.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
public class AccountsController {

    private final Logger logger = LoggerFactory.getLogger(AccountsController.class.getName());
    private AccountRepository accountRepository;

    @Autowired
    public AccountsController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        logger.info("AccountRepository says system has {} accounts", accountRepository.countAccounts());
    }

    @RequestMapping("/accounts/{accountNumber}")
    public Account byNumber(@PathVariable("accountNumber") String accountNumber) {

        logger.info("accounts-service byNumber() invoked: {}", accountNumber);
        Account account = accountRepository.findByNumber(accountNumber);
        logger.info("accounts-service byNumber() found: {}", account);

        if (account == null)
            throw new AccountNotFoundException(accountNumber);
        else {
            return account;
        }
    }

    @RequestMapping("/accounts/owner/{name}")
    public List<Account> byOwner(@PathVariable("name") String partialName) {
        logger.info("accounts-service byOwner() invoked: {} for {}",
                accountRepository.getClass().getName() ,partialName);

        List<Account> accounts = accountRepository.findByOwnerContainingIgnoreCase(partialName);
        logger.info("accounts-service byOwner() found: {}", accounts);

        if (accounts == null || accounts.size() == 0)
            throw new AccountNotFoundException(partialName);
        else {
            return accounts;
        }
    }
}
