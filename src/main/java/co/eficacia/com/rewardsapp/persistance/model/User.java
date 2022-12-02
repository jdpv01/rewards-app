package co.eficacia.com.rewardsapp.persistance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Data
@Entity
@Getter
@Setter
@Table(name = "`user`", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "`user_role`",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roleSet = new HashSet<>();

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate birthDate;

    private boolean subscribed;

    private String phoneNumber;

    private String email;

    private String password;

    private int currentPoints;

    private int redeemedPoints;

    private int pendingPoints;

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

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
