package co.eficacia.com.rewardsapp.persistance.model;

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
@Table(name = "`reward`")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reward {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String description;

    private String link;

    private String code;

    private String image;

    private int availableQuantity;

    private int requiredPoints;

    private ZonedDateTime validityDate;

    @OneToMany(mappedBy = "reward")
    private List<RedeemedTransaction> redeemedTransactionList;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
