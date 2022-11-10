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
@Table(name = "`invoicePromotion`")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoicePromotion {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private  Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "promotion_id", nullable = false)
    private Promotion promotion;

    private int quantity;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
