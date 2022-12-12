package co.eficacia.com.rewardsapp.web.api;

import co.eficacia.com.rewardsapp.web.dto.AvailableRewardDTO;
import co.eficacia.com.rewardsapp.web.dto.RedeemedRewardDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/rewards")
public interface RewardAPI {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-reward")
    AvailableRewardDTO createReward(@RequestBody AvailableRewardDTO availableRewardDTO);

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/request-reward")
    RedeemedRewardDTO requestReward(@RequestParam UUID id);

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/get-reward")
    AvailableRewardDTO getReward(@RequestParam UUID id);

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/get-all-rewards")
    List<AvailableRewardDTO> getRewards();

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-reward")
    AvailableRewardDTO updateReward(@RequestParam UUID id, @RequestBody AvailableRewardDTO availableRewardDTO);

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-reward")
    boolean deleteReward(@RequestParam UUID id);
}
