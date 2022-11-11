package co.eficacia.com.rewardsapp.web.controller;

import co.eficacia.com.rewardsapp.mapper.AccumulatedTransactionMapper;
import co.eficacia.com.rewardsapp.service.AccumulatedTransactionService;
import co.eficacia.com.rewardsapp.web.api.AccumulatedTransactionAPI;
import co.eficacia.com.rewardsapp.web.dto.AccumulatedTransactionDTO;

import co.eficacia.com.rewardsapp.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class AccumulatedTransactionController implements AccumulatedTransactionAPI {

    private final AccumulatedTransactionService accumulatedTransactionservice;

    private final AccumulatedTransactionMapper  accumulatedTransactionMapper;

    @Override
    public AccumulatedTransactionDTO createAccumulatedTransaction(AccumulatedTransactionDTO accumulatedTransactionDTO) {
        return accumulatedTransactionMapper.fromAccumulatedTransaction(accumulatedTransactionservice.createAccumulatedTransaction(accumulatedTransactionMapper.fromAccumulatedTransactionDTO(accumulatedTransactionDTO)));
    }

    @Override
    public AccumulatedTransactionDTO getAccumulatedTransactionDTO(UUID id) {
        return accumulatedTransactionMapper.fromAccumulatedTransaction(accumulatedTransactionservice.getAccumulatedTransaction(id));
    }

    @Override
    public List<AccumulatedTransactionDTO> getAccumulatedTransactions() {
        return accumulatedTransactionservice.getAccumulatedTransactions().stream().map(accumulatedTransactionMapper::fromAccumulatedTransaction).collect(Collectors.toList());
    }

    @Override
    public AccumulatedTransactionDTO updateAccumulatedTransaction(UUID id, AccumulatedTransactionDTO accumulatedTransactionDTO) {
        return accumulatedTransactionMapper.fromAccumulatedTransaction(accumulatedTransactionservice.updateAccumulatedTransaction(accumulatedTransactionMapper.fromAccumulatedTransactionDTO(id, accumulatedTransactionDTO)));
    }

    @Override
    public boolean deleteAccumulatedTransaction(UUID id) {
        return accumulatedTransactionservice.deleteAccumulatedTransaction(id);
    }

    @Override
    public Integer currentUserPoint(UserDTO user) {
        return accumulatedTransactionservice.currentUserPoint(user);
    }


}
