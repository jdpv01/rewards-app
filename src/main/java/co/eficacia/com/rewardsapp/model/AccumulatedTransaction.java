package co.eficacia.com.rewardsapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Table(name = "`AccumulatedTransaction`")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccumulatedTransaction {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private User user;

    private String source;

    private int availableQuantity;

    private int accumulatedPoints;

    private ZonedDateTime timestamp;

    @OneToOne(mappedBy = "accumulatedTransaction")
    private Invoice invoice;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
