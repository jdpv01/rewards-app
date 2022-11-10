package co.eficacia.com.rewardsapp.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "`invoice`")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToMany
    @JoinTable(
            name = "promotion_invoice",
            joinColumns = @JoinColumn(name = "promotion_id"),
            inverseJoinColumns = @JoinColumn(name = "invoice_id"))
    private List<Promotion> promotionList;

    private String image;

    private String code;

    private String state;

    @OneToOne(mappedBy = "invoice")
    private AccumulatedTransaction accumulatedTransaction;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
