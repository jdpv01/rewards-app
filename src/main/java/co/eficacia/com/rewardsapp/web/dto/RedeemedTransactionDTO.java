package co.eficacia.com.rewardsapp.web.dto;

import co.eficacia.com.rewardsapp.persistance.model.Reward;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RedeemedTransactionDTO {

    private Reward reward;

    private String source;

    private int redeemedPoints;

    private ZonedDateTime timestamp;
}
