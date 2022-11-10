package co.eficacia.com.rewardsapp.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Table(name = "`accumulatedTransaction`")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccumulatedTransaction {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String source;

    private int productQuantity;

    private int accumulatedPoints;

    private ZonedDateTime timestamp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    private Invoice invoice;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
