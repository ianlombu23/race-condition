package com.woyo.racecondition.service;

import com.woyo.racecondition.dto.TransferRequestDto;
import com.woyo.racecondition.entity.Account;
import com.woyo.racecondition.exception.CustomException;
import com.woyo.racecondition.repository.AccountRepository;
import com.woyo.racecondition.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public String transfer(TransferRequestDto request) {
        Account account = getOriginAccount(request);
        log.info("Account Balance: {}", account.getBalance());
        validateSufficientBalance(request, account);
        Account beneficiaryAccount = getBeneficiaryAccount(request);
        updateAccount(request, beneficiaryAccount, account);

        return "Transfer Success";
    }

    private void updateAccount(TransferRequestDto request, Account beneficiaryAccount, Account account) {
        beneficiaryAccount.setBalance(beneficiaryAccount.getBalance().add(request.getAmountToTransfer()));
        account.setBalance(account.getBalance().subtract(request.getAmountToTransfer()));

        List<Account> accounts = List.of(account, beneficiaryAccount);
        accountRepository.saveAll(accounts);
    }


    private Account getOriginAccount(TransferRequestDto request) {
        return accountRepository.findByCustomerId(request.getCustomerId())
                .orElseThrow(() -> new CustomException("4500", "Customer tidak ditemukan"));
    }

    private Account getBeneficiaryAccount(TransferRequestDto request) {
        return accountRepository.findByAccountNumber(request.getBeneficiaryAccountNumber())
                .orElseThrow(() -> new CustomException("4600", "AccountNumber tidak ditemukan"));
    }

    private static void validateSufficientBalance(TransferRequestDto request, Account account) {
        if (account.getBalance().compareTo(request.getAmountToTransfer()) < 0) {
            throw new CustomException("4500", "Saldo tidak cukup");
        }
    }
}
