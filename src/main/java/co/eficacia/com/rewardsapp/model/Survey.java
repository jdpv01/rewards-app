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
@Table(name = "`Survey`")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Survey {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String title;

    private String description;

    private int offeredPoints;

    private ZonedDateTime validityDate;

    @OneToMany(mappedBy = "survey")
    private List<Question> questionList;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
