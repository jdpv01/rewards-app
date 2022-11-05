package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.StoreErrorCode;
import co.eficacia.com.rewardsapp.error.ObjectError;
import co.eficacia.com.rewardsapp.error.exception.GlobalException;
import co.eficacia.com.rewardsapp.model.Store;
import co.eficacia.com.rewardsapp.repository.StoreRepository;
import co.eficacia.com.rewardsapp.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store getStore(UUID id) {
        Optional<Store> optionalStore = storeRepository.findById(id);
        if(optionalStore.isPresent())
            return optionalStore.get();
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(StoreErrorCode.CODE_01, StoreErrorCode.CODE_01.getMessage()));
    }

    @Override
    public List<Store> getStores() {
        return StreamSupport.stream(storeRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Store updateStore(Store store) {
        Optional<Store> optionalStore = storeRepository.findById(store.getId());
        if(optionalStore.isPresent())
            return storeRepository.save(store);
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(StoreErrorCode.CODE_01, StoreErrorCode.CODE_01.getMessage()));
    }

    @Override
    public boolean deleteStore(UUID id) {
        Optional<Store> optionalStore = storeRepository.findById(id);
        if(optionalStore.isPresent()) {
            storeRepository.delete(optionalStore.get());
            return true;
        }
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(StoreErrorCode.CODE_01, StoreErrorCode.CODE_01.getMessage()));
    }
}
