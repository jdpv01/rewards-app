package co.eficacia.com.rewardsapp.api;

import co.eficacia.com.rewardsapp.dto.StoreDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
