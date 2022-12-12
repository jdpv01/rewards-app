package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.RewardErrorCode;
import co.eficacia.com.rewardsapp.web.error.ObjectError;
import co.eficacia.com.rewardsapp.web.error.exception.CustomException;
import co.eficacia.com.rewardsapp.persistance.model.Reward;
import co.eficacia.com.rewardsapp.persistance.repository.RewardRepository;
import co.eficacia.com.rewardsapp.service.RewardService;
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
public class RewardServiceImpl implements RewardService {

    public final static String REWARDS_FOLDER = "rewards/";
    private final RewardRepository rewardRepository;

    @Override
    public Reward createReward(Reward reward) {
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
}