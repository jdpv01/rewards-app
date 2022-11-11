package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.model.Reward;

import java.util.List;
import java.util.UUID;

public interface RewardService {

    Reward createReward(Reward reward);

    Reward getReward(UUID id);

    List<Reward> getRewards();

    Reward updateReward(Reward reward);

    boolean deleteReward(UUID id);
}
