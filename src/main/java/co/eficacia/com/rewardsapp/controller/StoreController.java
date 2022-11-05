package co.eficacia.com.rewardsapp.controller;

import co.eficacia.com.rewardsapp.api.StoreAPI;
import co.eficacia.com.rewardsapp.dto.StoreDTO;
import co.eficacia.com.rewardsapp.mapper.StoreMapper;
import co.eficacia.com.rewardsapp.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class StoreController implements StoreAPI {

    private final StoreService storeService;
    private final StoreMapper storeMapper;

    @Override
    public StoreDTO createStore(StoreDTO storeDTO) {
        return storeMapper.fromStore(storeService.createStore(storeMapper.fromStoreDTO(storeDTO)));
    }

    @Override
    public StoreDTO getStore(UUID id) {
        return storeMapper.fromStore(storeService.getStore(id));
    }

    @Override
    public List<StoreDTO> getStores() {
        return storeService.getStores().stream().map(storeMapper::fromStore).collect(Collectors.toList());
    }

    @Override
    public StoreDTO updateStore(UUID id, StoreDTO storeDTO) {
        return storeMapper.fromStore(storeService.updateStore(storeMapper.fromStoreDTO(id, storeDTO)));
    }

    @Override
    public boolean deleteStore(UUID id) {
        return storeService.deleteStore(id);
    }
}
