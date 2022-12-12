package co.eficacia.com.rewardsapp.web.controller;

import co.eficacia.com.rewardsapp.mapper.RedeemedTransactionMapper;
import co.eficacia.com.rewardsapp.service.RedeemedTransactionService;
import co.eficacia.com.rewardsapp.web.api.RedeemedTransactionAPI;
import co.eficacia.com.rewardsapp.web.dto.RedeemedTransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class RedeemedTransactionController implements RedeemedTransactionAPI {

    private final RedeemedTransactionService redeemedTransactionService;

    private final RedeemedTransactionMapper redeemedTransactionMapper;

    @Override
    public RedeemedTransactionDTO createRedeemedTransaction(RedeemedTransactionDTO redeemedTransactionDTO) {
        return redeemedTransactionMapper.fromRedeemedTransaction(redeemedTransactionService.createRedeemedTransaction(redeemedTransactionMapper.fromRedeemedTransactionDTO(redeemedTransactionDTO)));
    }

    @Override
    public RedeemedTransactionDTO getRedeemedTransaction(UUID id) {
        return redeemedTransactionMapper.fromRedeemedTransaction(redeemedTransactionService.getRedeemedTransaction(id));
    }

    @Override
    public List<RedeemedTransactionDTO> getRedeemedTransactions() {
        return redeemedTransactionService.getRedeemedTransactions().stream().map(redeemedTransactionMapper::fromRedeemedTransaction).collect(Collectors.toList());
    }

    @Override
    public RedeemedTransactionDTO updateRedeemedTransaction(UUID id, RedeemedTransactionDTO redeemedTransactionDTO) {
        return redeemedTransactionMapper.fromRedeemedTransaction(redeemedTransactionService.updateRedeemedTransaction(redeemedTransactionMapper.fromRedeemedTransactionDTO(id, redeemedTransactionDTO)));
    }

    @Override
    public boolean deleteRedeemedTransaction(UUID id) {
        return redeemedTransactionService.deleteRedeemedTransaction(id);
    }
}
