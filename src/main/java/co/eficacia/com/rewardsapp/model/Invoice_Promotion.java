package co.eficacia.com.rewardsapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table(name = "`Invoice_Promotion`")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice_Promotion {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Promotion promotion;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
