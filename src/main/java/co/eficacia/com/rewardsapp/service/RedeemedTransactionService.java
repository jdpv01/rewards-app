package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.model.RedeemedTransaction;
import co.eficacia.com.rewardsapp.model.User;

import java.util.List;
import java.util.UUID;

public interface RedeemedTransactionService {

    RedeemedTransaction createRedeemedTransaction(RedeemedTransaction redeemedTransaction);

    RedeemedTransaction getRedeemedTransaction(UUID id);

    List<RedeemedTransaction> getRedeemedTransactions();

    RedeemedTransaction updateRedeemedTransaction(RedeemedTransaction redeemedTransaction);

    boolean deleteRedeemedTransaction(UUID id);

    Integer redeemedCurrentPointsUser(User user);
}
