package co.edu.icesi.calizoowebapp.model;

import co.edu.icesi.calizoowebapp.constants.AnimalSex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name = "`africanLion`")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AfricanLion {

    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    private String name;

    private AnimalSex sex;

    private double weight;

    private int age;

    private double height;

    private LocalDateTime arrivedZooDate;

    private UUID fatherId;

    private UUID motherId;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID();
    }

}
