package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.model.Store;

import java.util.List;
import java.util.UUID;

public interface StoreService {

    Store createStore(Store store);

    Store getStore(UUID id);

    Store searchStoreByName(String name);

    List<Store> getStores();

    Store updateStore(Store store);

    boolean deleteStore(UUID id);
}