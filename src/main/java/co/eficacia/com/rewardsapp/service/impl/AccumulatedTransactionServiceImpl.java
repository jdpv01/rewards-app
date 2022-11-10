package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.AccumulatedTransactionErrorCode;
import co.eficacia.com.rewardsapp.error.ObjectError;
import co.eficacia.com.rewardsapp.error.exception.GlobalException;
import co.eficacia.com.rewardsapp.model.AccumulatedTransaction;
import co.eficacia.com.rewardsapp.repository.AccumulatedTransactionRepository;
import co.eficacia.com.rewardsapp.service.AccumulatedTransactionService;
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
public class AccumulatedTransactionServiceImpl implements AccumulatedTransactionService{

    private final AccumulatedTransactionRepository AccumulatedTransactionRepository;

    @Override
    public AccumulatedTransaction createAccumulatedTransaction(AccumulatedTransaction accumulatedTransaction) {
        return AccumulatedTransactionRepository.save(accumulatedTransaction);
    }

    @Override
    public AccumulatedTransaction getAccumulatedTransaction(UUID id) {
        Optional<AccumulatedTransaction> optionalAccumulatedTransaction = AccumulatedTransactionRepository.findById(id);
        if(optionalAccumulatedTransaction.isPresent())
            return optionalAccumulatedTransaction.get();
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(AccumulatedTransactionErrorCode.CODE_01, AccumulatedTransactionErrorCode.CODE_01.getMessage()));
    }

    @Override
    public List<AccumulatedTransaction> getAccumulatedTransactions() {
        return StreamSupport.stream(AccumulatedTransactionRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public AccumulatedTransaction updateAccumulatedTransaction(AccumulatedTransaction AccumulatedTransaction) {
        Optional<AccumulatedTransaction> optionalAccumulatedTransaction = AccumulatedTransactionRepository.findById(AccumulatedTransaction.getId());
        if(optionalAccumulatedTransaction.isPresent())
            return AccumulatedTransactionRepository.save(AccumulatedTransaction);
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(AccumulatedTransactionErrorCode.CODE_01, AccumulatedTransactionErrorCode.CODE_01.getMessage()));
    }

    @Override
    public boolean deleteAccumulatedTransaction(UUID id) {
        Optional<AccumulatedTransaction> optionalAccumulatedTransaction = AccumulatedTransactionRepository.findById(id);
        if(optionalAccumulatedTransaction.isPresent()) {
            AccumulatedTransactionRepository.delete(optionalAccumulatedTransaction.get());
            return true;
        }
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(AccumulatedTransactionErrorCode.CODE_01, AccumulatedTransactionErrorCode.CODE_01.getMessage()));
    }

    public punto sumarpuntosusuario(){

    }

}
