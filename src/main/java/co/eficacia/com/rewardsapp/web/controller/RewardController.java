package co.eficacia.com.rewardsapp.web.controller;

import co.eficacia.com.rewardsapp.web.api.RewardAPI;
import co.eficacia.com.rewardsapp.web.dto.AvailableRewardDTO;
import co.eficacia.com.rewardsapp.mapper.RewardMapper;
import co.eficacia.com.rewardsapp.service.RewardService;
import co.eficacia.com.rewardsapp.web.dto.RedeemedRewardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class RewardController implements RewardAPI {

    private final RewardService rewardService;
    private final RewardMapper rewardMapper;

    @Override
    public AvailableRewardDTO createReward(AvailableRewardDTO availableRewardDTO) {
        return rewardMapper.fromReward(rewardService.createReward(rewardMapper.fromRewardDTO(availableRewardDTO)));
    }

    @Override
    public RedeemedRewardDTO requestReward(@RequestParam UUID id) {
        return null;
    }

    @Override
    public AvailableRewardDTO getReward(UUID id) {
        return rewardMapper.fromReward(rewardService.getReward(id));
    }

    @Override
    public List<AvailableRewardDTO> getRewards() {
        return rewardService.getRewards().stream().map(rewardMapper::fromReward).collect(Collectors.toList());
    }

    @Override
    public AvailableRewardDTO updateReward(UUID id, AvailableRewardDTO availableRewardDTO) {
        return rewardMapper.fromReward(rewardService.updateReward(rewardMapper.fromRewardDTO(id, availableRewardDTO)));
    }

    @Override
    public boolean deleteReward(UUID id) {
        return rewardService.deleteReward(id);
    }
}
