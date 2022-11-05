package co.eficacia.com.rewardsapp.dto;

import co.eficacia.com.rewardsapp.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String role;

    private String name;

    private String lastName;

    private String gender;

    private LocalDate birthDate;

    private boolean subscribed;

    private String phoneNumber;

    private String email;

    private String password;

    private String matchingPassword;

    private List<AccumulatedTransaction> accumulatedTransactionList;

    private List<RedeemedTransaction> redeemedTransactionList;

    private List<Invoice> invoiceList;

    private List<Comment> commentList;

    private List<Answer> answerList;
}