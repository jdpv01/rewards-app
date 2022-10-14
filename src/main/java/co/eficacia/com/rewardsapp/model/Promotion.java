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
@Table(name = "`Promotion`")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String name;

    private List<String> codeList;

    private String content;

    private String image;

    private int offeredPoints;

    private ZonedDateTime validityDate;

    @OneToMany(mappedBy = "Promotion")
    private List<Invoice_Promotion> invoice_promotionList;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
