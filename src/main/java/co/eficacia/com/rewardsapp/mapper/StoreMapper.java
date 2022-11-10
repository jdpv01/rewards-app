package co.eficacia.com.rewardsapp.mapper;

import co.eficacia.com.rewardsapp.web.dto.StoreDTO;
import co.eficacia.com.rewardsapp.persistance.model.Store;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    Store fromStoreDTO(StoreDTO storeDTO);

    Store fromStoreDTO(UUID id, StoreDTO storeDTO);

    StoreDTO fromStore(Store store);
}
