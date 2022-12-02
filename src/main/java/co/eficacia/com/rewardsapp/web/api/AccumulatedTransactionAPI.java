package co.eficacia.com.rewardsapp.web.api;

import co.eficacia.com.rewardsapp.persistance.model.Survey;
import co.eficacia.com.rewardsapp.web.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/AccumulatedTransactions")
public interface AccumulatedTransactionAPI {

    @PostMapping("/create-accumulatedTransaction")
    AccumulatedTransactionDTO createAccumulatedTransaction(@RequestBody AccumulatedTransactionDTO accumulatedTransactionDTO);

    @GetMapping("/get-accumulatedTransaction/{id}")
    AccumulatedTransactionDTO getAccumulatedTransactionDTO(@PathVariable UUID id);

    @GetMapping("/get-all-accumulatedTransactions")
    List<AccumulatedTransactionDTO> getAccumulatedTransactions();

    @PutMapping("/update-accumulatedTransaction/{id}")
    AccumulatedTransactionDTO updateAccumulatedTransaction(@PathVariable UUID id,@RequestBody AccumulatedTransactionDTO accumulatedTransactionDTO);

    @DeleteMapping("/delete-accumulatedTransaction/{id}")
    boolean deleteAccumulatedTransaction(@PathVariable UUID id);

    @GetMapping("/get-user-current-point/{id}")
    UserDTO currentUserPoint(UserDTO user);

    @PostMapping("/add-transaction-comment/{id}")
    void addTransactionComment(UserDTO userDTO, PublicationDTO publicationDTO, CommentDTO commentDTO);

    @PostMapping("/add-transaction-survey/{id}")
    void addTransactionSurvey(UserDTO userDTO, SurveyDTO surveyDTO);
}
