package co.eficacia.com.rewardsapp.web.api;

import co.eficacia.com.rewardsapp.web.dto.RedeemedTransactionDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/redeemed-transactions")
public interface RedeemedTransactionAPI {

    @PostMapping("/create-redeemed-transaction")
    RedeemedTransactionDTO createRedeemedTransaction(@RequestBody RedeemedTransactionDTO redeemedTransactionDTO);

    @GetMapping("/get-redeemed-transaction/{id}")
    RedeemedTransactionDTO getRedeemedTransaction(@PathVariable UUID id);

    @GetMapping("/get-all-redeemed-transactions")
    List<RedeemedTransactionDTO> getRedeemedTransactions();

    @PutMapping("/update-redeemed-transaction/{id}")
    RedeemedTransactionDTO updateRedeemedTransaction(@PathVariable UUID id, @RequestBody RedeemedTransactionDTO redeemedTransactionDTO);

    @GetMapping("/delete-redeemed-transaction/{id}")
    boolean deleteRedeemedTransaction(@PathVariable UUID id);
}
