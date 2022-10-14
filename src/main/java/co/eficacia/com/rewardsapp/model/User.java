package co.eficacia.com.rewardsapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "`User`")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String name;

    private String role;

    private String lastName;

    private String gender;

    private LocalDate birthDate;

    private boolean subscribed;

    private String phoneNumber;

    private String emailAddress;

    @OneToMany(mappedBy = "user")
    private List<AccumulatedTransaction>  accumulatedTransactionList;

    @OneToMany(mappedBy = "user")
    private List<RedeemedTransaction> redeemedTransactionList;

    @OneToMany(mappedBy = "user")
    private List<Invoice> invoiceList;

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "user")
    private List<Answer> answerList;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
