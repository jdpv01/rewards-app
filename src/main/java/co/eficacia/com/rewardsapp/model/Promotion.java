package co.eficacia.com.rewardsapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Data
@Table(name = "`promotion`")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @OneToMany(mappedBy = "Promotion")
    private List<InvoicePromotion>  invoicePromotionList;

    private String name;

    private String content;

    private String image;

    private int offeredPoints;

    private int quantityPromotions;

    private ZonedDateTime validityDate;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}