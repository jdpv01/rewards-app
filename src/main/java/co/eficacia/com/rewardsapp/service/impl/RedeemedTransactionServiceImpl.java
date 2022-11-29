package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.RedeemedTransactionErrorCode;
import co.eficacia.com.rewardsapp.mapper.UserMapper;
import co.eficacia.com.rewardsapp.web.error.ObjectError;
import co.eficacia.com.rewardsapp.web.error.exception.GlobalException;
import co.eficacia.com.rewardsapp.persistance.repository.RedeemedTransactionRepository;
import co.eficacia.com.rewardsapp.persistance.model.RedeemedTransaction;
import co.eficacia.com.rewardsapp.persistance.model.User;
import co.eficacia.com.rewardsapp.service.RedeemedTransactionService;
import co.eficacia.com.rewardsapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class RedeemedTransactionServiceImpl implements RedeemedTransactionService {

    private final RedeemedTransactionRepository redeemedTransactionRepository;

    @Autowired
    private UserService userService;

    @Override
    public RedeemedTransaction createRedeemedTransaction(RedeemedTransaction redeemedTransaction) {
        return redeemedTransactionRepository.save(redeemedTransaction);
    }

    @Override
    public RedeemedTransaction getRedeemedTransaction(UUID id) {
        Optional<RedeemedTransaction> optionalRedeemedTransaction = redeemedTransactionRepository.findById(id);
        if (optionalRedeemedTransaction.isPresent())
            return optionalRedeemedTransaction.get();
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(RedeemedTransactionErrorCode.CODE_01, RedeemedTransactionErrorCode.CODE_01.getMessage()));
    }

    @Override
    public List<RedeemedTransaction> getRedeemedTransactions() {
        return StreamSupport.stream(redeemedTransactionRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public RedeemedTransaction updateRedeemedTransaction(RedeemedTransaction redeemedTransaction) {
        Optional<RedeemedTransaction> optionalRedeemedTransaction = redeemedTransactionRepository.findById(redeemedTransaction.getId());
        if (optionalRedeemedTransaction.isPresent())
            return redeemedTransactionRepository.save(redeemedTransaction);
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(RedeemedTransactionErrorCode.CODE_01, RedeemedTransactionErrorCode.CODE_01.getMessage()));
    }

    @Override
    public boolean deleteRedeemedTransaction(UUID id) {
        Optional<RedeemedTransaction> optionalRedeemedTransaction = redeemedTransactionRepository.findById(id);
        if (optionalRedeemedTransaction.isPresent()) {
            redeemedTransactionRepository.delete(optionalRedeemedTransaction.get());
            return true;
        }
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(RedeemedTransactionErrorCode.CODE_01, RedeemedTransactionErrorCode.CODE_01.getMessage()));
    }

    @Override
    public Integer redeemedCurrentPointsUser(User user) {
        List<RedeemedTransaction> transactions = StreamSupport.stream(redeemedTransactionRepository.findAll().spliterator(), false).collect(Collectors.toList());
        for (RedeemedTransaction transactionA : transactions) {
            if (transactionA.getUser().getId() == user.getId()) {
                user.setRedeemedPoint(user.getRedeemedPoint() + transactionA.getRedeemedPoints());
            }
        }
        //userService.updateUser(user);

        return user.getRedeemedPoint();
    }

}
