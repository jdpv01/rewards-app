package co.eficacia.com.rewardsapp.api;

import co.eficacia.com.rewardsapp.dto.RewardDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/rewards")
public interface RewardAPI {

    @PostMapping("/create-reward")
    RewardDTO createReward(@RequestBody RewardDTO rewardDTO);

    @GetMapping("/get-reward/{id}")
    RewardDTO getReward(@PathVariable UUID id);

    @GetMapping("/get-all-rewards")
    List<RewardDTO> getRewards();

    @PutMapping("/update-reward/{id}")
    RewardDTO updateReward(@PathVariable UUID id, @RequestBody RewardDTO rewardDTO);

    @DeleteMapping("/delete-reward/{id}")
    boolean deleteReward(@PathVariable UUID id);
}
