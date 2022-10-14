package co.eficacia.com.rewardsapp.dto;

import co.eficacia.com.rewardsapp.model.Invoice_Promotion;
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
public class PromotionDTO {

    private UUID id;

    private String name;

    private List<String> codeList;

    private String content;

    private String image;

    private int offeredPoints;

    private ZonedDateTime validityDate;

    private List<Invoice_Promotion> invoice_promotionList;
}
