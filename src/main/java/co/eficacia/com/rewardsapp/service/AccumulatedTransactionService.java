package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.model.AccumulatedTransaction;

import java.util.List;
import java.util.UUID;

public interface AccumulatedTransactionService {

    AccumulatedTransaction createAccumulatedTransaction(AccumulatedTransaction accumulatedTransaction);

    AccumulatedTransaction getAccumulatedTransaction(UUID id);

    List<AccumulatedTransaction> getAccumulatedTransactions();

    AccumulatedTransaction updateAccumulatedTransaction(AccumulatedTransaction accumulatedTransaction);

    boolean deleteAccumulatedTransaction(UUID id);

}
