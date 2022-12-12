package co.eficacia.com.rewardsapp.web.api;

import co.eficacia.com.rewardsapp.web.dto.PromotionDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/promotions")
public interface PromotionAPI {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-promotion")
    PromotionDTO createPromotion(@RequestBody PromotionDTO promotionDTO);

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/get-promotion")
    PromotionDTO getPromotion(@RequestParam UUID id);

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/get-all-promotions")
    List<PromotionDTO> getPromotions();

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-promotion")
    PromotionDTO updatePromotion(@RequestParam UUID id, @RequestBody PromotionDTO promotionDTO);

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-promotion")
    boolean deletePromotion(@RequestParam UUID id);
}
