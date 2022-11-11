package co.eficacia.com.rewardsapp.mapper;

import co.eficacia.com.rewardsapp.web.dto.RewardDTO;
import co.eficacia.com.rewardsapp.persistance.model.Reward;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface RewardMapper {

    Reward fromRewardDTO(RewardDTO rewardDTO);

    Reward fromRewardDTO(UUID id, RewardDTO rewardDTO);

    RewardDTO fromReward(Reward reward);
}