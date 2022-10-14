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
@Table(name = "`Question`")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Survey survey;

    private String content;

    private String type;

    @OneToOne(mappedBy = "question")
    private MultipleChoice multipleChoice;

    @OneToMany(mappedBy = "question")
    private List<Answer> answerList;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
