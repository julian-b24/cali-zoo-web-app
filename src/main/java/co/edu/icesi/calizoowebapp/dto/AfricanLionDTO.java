package co.edu.icesi.calizoowebapp.dto;

import co.edu.icesi.calizoowebapp.constants.AnimalSex;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivedZooDate;

    private UUID fatherId;

    private UUID motherId;

}
