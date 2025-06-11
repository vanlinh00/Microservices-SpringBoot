package com.example.deliveryservice.service;

import com.example.deliveryservice.entity.Account;
import com.example.deliveryservice.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentService {
    private final   AccountRepository accountRepository;

   // 2. Trường hợp có Locking (Pessimistic Locking)
    @Transactional
    public void processPayment(Long accountId, double amount) {

        Optional<Account> accountOptional = accountRepository.findById(accountId);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
            }
        }

    }

  //3. Trường hợp không có Locking (Optimistic Locking hoặc No Locking)
    @Transactional
    public void processPaymentWithNotLocking(Long accountId, double amount) {

        Optional<Account> accountOptional = accountRepository.findByIdNotLocking(accountId);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
            }
        }

    }



}
