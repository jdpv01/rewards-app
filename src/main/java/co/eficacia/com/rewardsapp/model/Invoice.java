package co.eficacia.com.rewardsapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "`Invoice`")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Store store;

    private String image;

    private String code;

    private String state;

    @OneToOne(mappedBy = "invoice")
    private AccumulatedTransaction accumulatedTransaction;

    @OneToMany(mappedBy = "invoice")
    private List<Invoice_Promotion> invoice_promotionList;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
