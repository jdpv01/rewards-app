package co.eficacia.com.rewardsapp.web.api;

import co.eficacia.com.rewardsapp.web.dto.StoreDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/stores")
public interface StoreAPI {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-store")
    StoreDTO createStore(@RequestBody StoreDTO storeDTO);

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-store")
    StoreDTO getStore(@RequestParam UUID id);

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/get-all-stores")
    List<StoreDTO> getStores();

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-store")
    StoreDTO updateStore(@RequestParam UUID id, @RequestBody StoreDTO storeDTO);

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-store")
    boolean deleteStore(@RequestParam UUID id);
}
