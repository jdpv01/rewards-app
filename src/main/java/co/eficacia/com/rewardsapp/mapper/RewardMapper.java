package co.eficacia.com.rewardsapp.mapper;

import co.eficacia.com.rewardsapp.web.dto.AvailableRewardDTO;
import co.eficacia.com.rewardsapp.persistance.model.Reward;
import co.eficacia.com.rewardsapp.web.dto.RedeemedRewardDTO;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface RewardMapper {

    Reward fromRewardDTO(AvailableRewardDTO availableRewardDTO);

    Reward fromRewardDTO(UUID id, AvailableRewardDTO availableRewardDTO);

    AvailableRewardDTO fromRewardToAvailableRewardDTO(Reward reward);

    RedeemedRewardDTO fromRewardToRedeemedRewardDTO(Reward reward);
}