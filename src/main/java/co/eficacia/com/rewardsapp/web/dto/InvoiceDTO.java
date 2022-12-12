package co.eficacia.com.rewardsapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    private UUID id;

    private String storeName;

    private String state;

    private Integer pendingPoints;

    private List<String> promotionNameList;

    private ZonedDateTime timestamp;
}
