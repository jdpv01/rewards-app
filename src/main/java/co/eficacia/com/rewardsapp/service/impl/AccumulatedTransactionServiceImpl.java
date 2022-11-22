package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.AccumulatedTransactionErrorCode;
import co.eficacia.com.rewardsapp.persistance.model.*;
import co.eficacia.com.rewardsapp.web.error.ObjectError;
import co.eficacia.com.rewardsapp.web.error.exception.GlobalException;
import co.eficacia.com.rewardsapp.persistance.repository.AccumulatedTransactionRepository;
import co.eficacia.com.rewardsapp.service.AccumulatedTransactionService;
import co.eficacia.com.rewardsapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AccumulatedTransactionServiceImpl implements AccumulatedTransactionService {

    private final AccumulatedTransactionRepository accumulatedTransactionRepository;

    @Autowired
    private UserService userService;

    @Override
    public AccumulatedTransaction createAccumulatedTransaction(AccumulatedTransaction accumulatedTransaction) {
        return accumulatedTransactionRepository.save(accumulatedTransaction);
    }

    @Override
    public AccumulatedTransaction getAccumulatedTransaction(UUID id) {
        Optional<AccumulatedTransaction> optionalAccumulatedTransaction = accumulatedTransactionRepository.findById(id);
        if (optionalAccumulatedTransaction.isPresent())
            return optionalAccumulatedTransaction.get();
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(AccumulatedTransactionErrorCode.CODE_01, AccumulatedTransactionErrorCode.CODE_01.getMessage()));
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
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(AccumulatedTransactionErrorCode.CODE_01, AccumulatedTransactionErrorCode.CODE_01.getMessage()));
    }

    @Override
    public boolean deleteAccumulatedTransaction(UUID id) {
        Optional<AccumulatedTransaction> optionalAccumulatedTransaction = accumulatedTransactionRepository.findById(id);
        if (optionalAccumulatedTransaction.isPresent()) {
            accumulatedTransactionRepository.delete(optionalAccumulatedTransaction.get());
            return true;
        }
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(AccumulatedTransactionErrorCode.CODE_01, AccumulatedTransactionErrorCode.CODE_01.getMessage()));
    }

    @Override                   //iduser
    public User currentUserPoint(User user) {
        List<AccumulatedTransaction> transactions = getAccumulatedTransactions();
        for (AccumulatedTransaction transaction : transactions) {
            if (transaction.getUser().getId().equals(user.getId())) {
                user.setCurrentPoint(user.getCurrentPoint() + transaction.getAccumulatedPoints());
            }
        }
        //Stream<AccumulatedTransaction> stream = transactions.stream().filter(transaction -> transaction.getUser().getId() == user.getId());
        userService.updateUser(user);
        return user;
    }

    @Override                                   //Publication
    public AccumulatedTransaction addTransactionComment(User user, Publication publication, Comment comment) {
        AccumulatedTransaction transaction = new AccumulatedTransaction();
        transaction.setUser(user);
        transaction.setSource(transaction.PUBLICATION);
        transaction.setTimestamp(comment.getTimestamp());
        transaction.setAccumulatedPoints(publication.getOfferedPoints());
        transaction.setProductQuantity(1);
        accumulatedTransactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public AccumulatedTransaction addTransactionSurvey(User user, Survey survey) {
        AccumulatedTransaction transaction = new AccumulatedTransaction();
        transaction.setUser(user);
        transaction.setSource(transaction.SURVEY);
        transaction.setTimestamp(ZonedDateTime.now());
        transaction.setAccumulatedPoints(survey.getOfferedPoints());
        transaction.setProductQuantity(1);
        accumulatedTransactionRepository.save(transaction);
        return transaction;
    }

}
