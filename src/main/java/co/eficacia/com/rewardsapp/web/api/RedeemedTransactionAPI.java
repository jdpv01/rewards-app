package co.eficacia.com.rewardsapp.web.api;

import co.eficacia.com.rewardsapp.web.dto.RedeemedTransactionDTO;
import co.eficacia.com.rewardsapp.web.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@RequestMapping("/redeemed-transactions")
public interface RedeemedTransactionAPI {

    @PostMapping("/create-redeemed-transaction")
    RedeemedTransactionDTO createRedeemedTransaction(RedeemedTransactionDTO redeemedTransactionDTO);

    @GetMapping("/get-redeemed-transaction")
    RedeemedTransactionDTO getRedeemedTransaction(UUID id);

    @GetMapping("/get-all-redeemed-transactions")
    List<RedeemedTransactionDTO> getRedeemedTransactions();

    @PutMapping("/update-redeemed-transaction")
    RedeemedTransactionDTO updateRedeemedTransaction(RedeemedTransactionDTO redeemedTransactionDTO);

    @GetMapping("/delete-redeemed-transaction/{id}")
    boolean deleteRedeemedTransaction(UUID id);

    @GetMapping("/redeemed-current-point-user")
    Integer redeemedCurrentPointsUser(UserDTO userDTO);
}
