package co.eficacia.com.rewardsapp.web.api;

import co.eficacia.com.rewardsapp.web.dto.StoreDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/stores")
public interface StoreAPI {

    @PostMapping("/create-store")
    StoreDTO createStore(@RequestBody StoreDTO storeDTO);

    @GetMapping("/get-store/{id}")
    StoreDTO getStore(@PathVariable UUID id);

    @GetMapping("/get-all-stores")
    List<StoreDTO> getStores();

    @PutMapping("/update-store/{id}")
    StoreDTO updateStore(@PathVariable UUID id, @RequestBody StoreDTO storeDTO);

    @DeleteMapping("/delete-store/{id}")
    boolean deleteStore(@PathVariable UUID id);
}
