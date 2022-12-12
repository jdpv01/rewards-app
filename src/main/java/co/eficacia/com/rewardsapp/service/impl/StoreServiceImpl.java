package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.StoreErrorCode;
import co.eficacia.com.rewardsapp.web.error.ObjectError;
import co.eficacia.com.rewardsapp.web.error.exception.CustomException;
import co.eficacia.com.rewardsapp.persistance.model.Store;
import co.eficacia.com.rewardsapp.persistance.repository.StoreRepository;
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

    public final static String STORES_FOLDER = "stores/";
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
        throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(StoreErrorCode.CODE_01, StoreErrorCode.CODE_01.getMessage()));
    }

    @Override
    public Store searchStoreByName(String name) {
        return storeRepository.searchStoreByName(name).orElseThrow();
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
        throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(StoreErrorCode.CODE_01, StoreErrorCode.CODE_01.getMessage()));
    }

    @Override
    public boolean deleteStore(UUID id) {
        Optional<Store> optionalStore = storeRepository.findById(id);
        if(optionalStore.isPresent()) {
            storeRepository.delete(optionalStore.get());
            return true;
        }
        throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(StoreErrorCode.CODE_01, StoreErrorCode.CODE_01.getMessage()));
    }
}
