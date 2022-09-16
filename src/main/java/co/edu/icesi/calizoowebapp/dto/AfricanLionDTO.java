package co.edu.icesi.calizoowebapp.dto;

import co.edu.icesi.calizoowebapp.constants.AnimalSex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AfricanLionDTO {

    private UUID id;

    private String name;

    private AnimalSex sex;

    private double weight;

    private int age;

    private double height;

    private LocalDateTime arrivedZooDate;

    private UUID fatherId;

    private UUID motherId;

}
