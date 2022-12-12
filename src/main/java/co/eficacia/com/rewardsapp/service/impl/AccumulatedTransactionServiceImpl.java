package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.AccumulatedTransactionErrorCode;
import co.eficacia.com.rewardsapp.persistance.model.*;
import co.eficacia.com.rewardsapp.persistance.repository.AccumulatedTransactionRepository;
import co.eficacia.com.rewardsapp.service.AccumulatedTransactionService;
import co.eficacia.com.rewardsapp.web.error.ObjectError;
import co.eficacia.com.rewardsapp.web.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AccumulatedTransactionServiceImpl implements AccumulatedTransactionService {

    private final AccumulatedTransactionRepository accumulatedTransactionRepository;

    @Override
    public AccumulatedTransaction createAccumulatedTransaction(AccumulatedTransaction accumulatedTransaction) {
        return accumulatedTransactionRepository.save(accumulatedTransaction);
    }

    @Override
    public AccumulatedTransaction getAccumulatedTransaction(UUID id) {
        Optional<AccumulatedTransaction> optionalAccumulatedTransaction = accumulatedTransactionRepository.findById(id);
        if  (optionalAccumulatedTransaction.isPresent())
            return optionalAccumulatedTransaction.get();
        throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(AccumulatedTransactionErrorCode.CODE_01, AccumulatedTransactionErrorCode.CODE_01.getMessage()));
    }

    @Override
    public List<AccumulatedTransaction> getAccumulatedTransactions() {
        return StreamSupport.stream(accumulatedTransactionRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public AccumulatedTransaction updateAccumulatedTransaction(AccumulatedTransaction AccumulatedTransaction) {
        Optional<AccumulatedTransaction> optionalAccumulatedTransaction = accumulatedTransactionRepository.findById(AccumulatedTransaction.getId());
        if (optionalAccumulatedTransaction.isPresent())
            return accumulatedTransactionRepository.save(AccumulatedTransaction);
        throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(AccumulatedTransactionErrorCode.CODE_01, AccumulatedTransactionErrorCode.CODE_01.getMessage()));
    }

    @Override
    public boolean deleteAccumulatedTransaction(UUID id) {
        Optional<AccumulatedTransaction> optionalAccumulatedTransaction = accumulatedTransactionRepository.findById(id);
        if (optionalAccumulatedTransaction.isPresent()) {
            accumulatedTransactionRepository.delete(optionalAccumulatedTransaction.get());
            return true;
        }
        throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(AccumulatedTransactionErrorCode.CODE_01, AccumulatedTransactionErrorCode.CODE_01.getMessage()));
    }
}
