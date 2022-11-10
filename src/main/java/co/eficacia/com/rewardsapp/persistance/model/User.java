package co.eficacia.com.rewardsapp.persistance.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "`user`")
@Getter
@Setter
public class User {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roleCollection;

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate birthDate;

    private boolean subscribed;

    private String phoneNumber;

    private String email;

    @Column(length = 60)
    private String password;

    private boolean enabled;

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

    public User() {
        super();
        this.enabled = false;
    }

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((getEmail() == null) ? 0 : getEmail().hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User user = (User) obj;
        if (!getEmail().equals(user.getEmail())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [id=")
                .append(id)
                .append(", firstName=").append(firstName)
                .append(", lastName=").append(lastName)
                .append(", gender=").append(gender)
                .append(", birthDate=").append(birthDate)
                .append(", subscribed=").append(subscribed)
                .append(", phoneNumber=").append(phoneNumber)
                .append(", email=").append(email)
                .append(", enabled=").append(enabled)
                .append(", roleCollection=").append(roleCollection)
                .append("]");
        return builder.toString();
    }
}
