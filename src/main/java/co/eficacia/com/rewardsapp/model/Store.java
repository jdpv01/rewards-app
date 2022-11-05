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
@Table(name = "`store`")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Store {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String name;

    private String image;

    @OneToMany(mappedBy = "store")
    private List<Invoice> invoiceList;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
