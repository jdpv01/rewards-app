package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.model.RedeemedTransaction;

import java.util.List;
import java.util.UUID;

public interface RedeemedTransactionService {

    RedeemedTransaction createRedeemedTransaction(RedeemedTransaction redeemedTransaction);

    RedeemedTransaction getRedeemedTransaction(UUID id);

    List<RedeemedTransaction> getRedeemedTransactions();

    RedeemedTransaction updateRedeemedTransaction(RedeemedTransaction redeemedTransaction);

    boolean deleteRedeemedTransaction(UUID id);
    
}
