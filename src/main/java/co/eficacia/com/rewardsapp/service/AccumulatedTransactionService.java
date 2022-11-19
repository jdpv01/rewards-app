package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.model.*;
import co.eficacia.com.rewardsapp.web.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface AccumulatedTransactionService {

    AccumulatedTransaction createAccumulatedTransaction(AccumulatedTransaction accumulatedTransaction);

    AccumulatedTransaction getAccumulatedTransaction(UUID id);

    List<AccumulatedTransaction> getAccumulatedTransactions();

    AccumulatedTransaction updateAccumulatedTransaction(AccumulatedTransaction accumulatedTransaction);

    boolean deleteAccumulatedTransaction(UUID id);

    Integer currentUserPoint(User user);

    //Publication
    void addTransactionComment(User user, Publication publication, Comment comment);

    void addTransactionSurvey(User user, Survey survey);
}
