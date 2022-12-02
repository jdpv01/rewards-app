package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.model.*;

import java.util.List;
import java.util.UUID;

public interface AccumulatedTransactionService {

    AccumulatedTransaction createAccumulatedTransaction(AccumulatedTransaction accumulatedTransaction);

    AccumulatedTransaction getAccumulatedTransaction(UUID id);

    List<AccumulatedTransaction> getAccumulatedTransactions();

    AccumulatedTransaction updateAccumulatedTransaction(AccumulatedTransaction accumulatedTransaction);

    boolean deleteAccumulatedTransaction(UUID id);

    User currentUserPoint(User user);

    //Publication
    AccumulatedTransaction addTransactionComment(User user, Publication publication, Comment comment);

    AccumulatedTransaction addTransactionSurvey(User user, Survey survey);
}
