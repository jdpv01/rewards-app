package co.eficacia.com.rewardsapp.web.controller;

import co.eficacia.com.rewardsapp.web.api.RewardAPI;
import co.eficacia.com.rewardsapp.web.dto.RewardDTO;
import co.eficacia.com.rewardsapp.mapper.RewardMapper;
import co.eficacia.com.rewardsapp.service.RewardService;
import lombok.RequiredArgsConstructor;
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
    public RewardDTO createReward(RewardDTO rewardDTO) {
        return rewardMapper.fromReward(rewardService.createReward(rewardMapper.fromRewardDTO(rewardDTO)));
    }

    @Override
    public RewardDTO getReward(UUID id) {
        return rewardMapper.fromReward(rewardService.getReward(id));
    }

    @Override
    public List<RewardDTO> getRewards() {
        return rewardService.getRewards().stream().map(rewardMapper::fromReward).collect(Collectors.toList());
    }

    @Override
    public RewardDTO updateReward(UUID id, RewardDTO rewardDTO) {
        return rewardMapper.fromReward(rewardService.updateReward(rewardMapper.fromRewardDTO(id, rewardDTO)));
    }

    @Override
    public boolean deleteReward(UUID id) {
        return rewardService.deleteReward(id);
    }
}
