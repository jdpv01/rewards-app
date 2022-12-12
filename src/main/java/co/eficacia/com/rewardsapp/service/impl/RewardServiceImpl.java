package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.RewardErrorCode;
import co.eficacia.com.rewardsapp.persistance.model.User;
import co.eficacia.com.rewardsapp.service.RedeemedTransactionService;
import co.eficacia.com.rewardsapp.service.UserService;
import co.eficacia.com.rewardsapp.web.error.ObjectError;
import co.eficacia.com.rewardsapp.web.error.exception.CustomException;
import co.eficacia.com.rewardsapp.persistance.model.Reward;
import co.eficacia.com.rewardsapp.persistance.repository.RewardRepository;
import co.eficacia.com.rewardsapp.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

    public final static String REWARDS_FOLDER = "rewards/";
    private final RewardRepository rewardRepository;

    private final UserService userService;

    @Override
    public Reward createReward(Reward reward) {
        //TODO
        return rewardRepository.save(reward);
    }

    @Override
    public Reward getReward(UUID id) {
        Optional<Reward> optionalReward = rewardRepository.findById(id);
        if(optionalReward.isPresent())
            return optionalReward.get();
        throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(RewardErrorCode.CODE_01, RewardErrorCode.CODE_01.getMessage()));
    }

    @Override
    public Reward requestReward(UUID userId, UUID rewardId) {
        User user = userService.getUser(userId);
        Reward reward = getReward(rewardId);
        if(user.getCurrentPoints() > reward.getRequiredPoints()) {
            userService.spendPoints(user, reward);
            reduceRewardQuantity(reward);
            return reward;
        }else {
            throw new CustomException(HttpStatus.BAD_REQUEST, new ObjectError(RewardErrorCode.CODE_02, RewardErrorCode.CODE_02.getMessage()));
        }
    }

    @Override
    public List<Reward> getRewards() {
        return StreamSupport.stream(rewardRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Reward updateReward(Reward reward) {
        Optional<Reward> optionalReward = rewardRepository.findById(reward.getId());
        if(optionalReward.isPresent())
            return rewardRepository.save(reward);
        throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(RewardErrorCode.CODE_01, RewardErrorCode.CODE_01.getMessage()));
    }

    @Override
    public boolean deleteReward(UUID id) {
        Optional<Reward> optionalReward = rewardRepository.findById(id);
        if(optionalReward.isPresent()) {
            rewardRepository.delete(optionalReward.get());
            return true;
        }
        throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(RewardErrorCode.CODE_01, RewardErrorCode.CODE_01.getMessage()));
    }

    @Override
    public void reduceRewardQuantity(Reward reward) {
        if(reward.getAvailableQuantity() >= 1){
            reward.setAvailableQuantity(reward.getAvailableQuantity()-1);
        }else{
            deleteReward(reward.getId());
        }
    }
}