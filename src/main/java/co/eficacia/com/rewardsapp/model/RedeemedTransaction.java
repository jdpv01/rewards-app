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
@Table(name = "`RedeemedTransaction`")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RedeemedTransaction {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Reward reward;

    private String source;

    private int redeemedPoints;

    private ZonedDateTime timestamp;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
