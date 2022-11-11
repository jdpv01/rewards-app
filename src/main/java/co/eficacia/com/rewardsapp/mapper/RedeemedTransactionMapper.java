package co.eficacia.com.rewardsapp.mapper;

import co.eficacia.com.rewardsapp.persistance.model.RedeemedTransaction;
import co.eficacia.com.rewardsapp.web.dto.RedeemedTransactionDTO;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface RedeemedTransactionMapper {

    RedeemedTransaction fromRedeemedTransactionDTO(RedeemedTransactionDTO redeemedTransactionDTO);

    RedeemedTransaction fromRedeemedTransactionDTO(UUID id, RedeemedTransactionDTO redeemedTransactionDTO);

    RedeemedTransactionDTO fromRedeemedTransaction(RedeemedTransaction redeemedTransaction);

}
