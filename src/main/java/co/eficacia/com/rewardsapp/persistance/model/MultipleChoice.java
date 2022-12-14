package co.eficacia.com.rewardsapp.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table(name = "`multipleChoice`")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MultipleChoice {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String choice1;

    private String choice2;

    private String choice3;

    private String choice4;

    @OneToOne(mappedBy = "multipleChoice")
    private Question question;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
