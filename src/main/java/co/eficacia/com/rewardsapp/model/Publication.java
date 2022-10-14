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
@Table(name = "`Publication`")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Publication {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String image;

    private String title;

    private String description;

    private int availableQuantity;

    private int offeredPoints;

    private ZonedDateTime validityDate;

    @OneToMany(mappedBy = "publication")
    private List<Comment> commentList;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}