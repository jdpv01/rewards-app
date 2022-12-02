package co.eficacia.com.rewardsapp.mapper;

import co.eficacia.com.rewardsapp.persistance.model.AccumulatedTransaction;
import co.eficacia.com.rewardsapp.web.dto.AccumulatedTransactionDTO;
import org.mapstruct.Mapper;

import java.util.UUID;
@Mapper(componentModel = "spring")
public interface AccumulatedTransactionMapper {

    AccumulatedTransaction fromAccumulatedTransactionDTO(AccumulatedTransactionDTO accumulatedTransactionDTO);

    AccumulatedTransaction fromAccumulatedTransactionDTO(UUID id, AccumulatedTransactionDTO accumulatedTransactionDTO);

    AccumulatedTransactionDTO fromAccumulatedTransaction(AccumulatedTransaction accumulatedTransaction);
}
