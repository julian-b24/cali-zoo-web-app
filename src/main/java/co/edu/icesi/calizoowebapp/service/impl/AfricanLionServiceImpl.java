package co.edu.icesi.calizoowebapp.service.impl;

import co.edu.icesi.calizoowebapp.constants.AfricanLionStandards;
import co.edu.icesi.calizoowebapp.constants.AnimalSex;
import co.edu.icesi.calizoowebapp.model.AfricanLion;
import co.edu.icesi.calizoowebapp.repository.AfricanLionRespository;
import co.edu.icesi.calizoowebapp.service.AfricanLionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class AfricanLionServiceImpl implements AfricanLionService {

    public final AfricanLionRespository africanLionRespository;

    @Override
    public AfricanLion getLion(String lionName) {
        return null;
    }

    @Override
    public AfricanLion createLion(AfricanLion africanLion) {
        lionsNameIsUnique(africanLion.getName());
        areLionCharacteristicsInStandards(africanLion);
        return africanLionRespository.save(africanLion);
    }

    @Override
    public List<AfricanLion> getLions() {
        return StreamSupport.stream(africanLionRespository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    private void lionsNameIsUnique(String name) {
        List<AfricanLion> lions = getLions();
        for (AfricanLion lion: lions) {
            if(lion.getName().equalsIgnoreCase(name)){
                throw new RuntimeException();
            }
        }
    }

    private void areLionCharacteristicsInStandards(AfricanLion africanLion) {
        validateLionsWeight(africanLion.getWeight(), africanLion.getSex());
        validateLionsAge(africanLion.getAge(), africanLion.getSex());
        validateLionsHeight(africanLion.getHeight(), africanLion.getSex());
    }

    private void validateLionsHeight(double height, AnimalSex sex) {

        double maxHeight = (sex.equals(AnimalSex.MALE)) ? AfricanLionStandards.MALE_MAX_HEIGHT_CM : AfricanLionStandards.FEMALE_MAX_HEIGHT_CM;
        double minHeight = (sex.equals(AnimalSex.MALE)) ? AfricanLionStandards.MALE_MIN_HEIGHT_CM : AfricanLionStandards.FEMALE_MIN_HEIGHT_CM;
        if(height > maxHeight || height < minHeight){
            throw new RuntimeException();
        }
    }

    private void validateLionsAge(int age, AnimalSex sex) {
        double maxAge = (sex.equals(AnimalSex.MALE)) ? AfricanLionStandards.MALE_MAX_AGE : AfricanLionStandards.FEMALE_MAX_AGE;
        double minAge = (sex.equals(AnimalSex.MALE)) ? AfricanLionStandards.MALE_MIN_AGE : AfricanLionStandards.FEMALE_MIN_AGE;
        if(age > maxAge || age < minAge){
            throw new RuntimeException();
        }
    }

    private void validateLionsWeight(double weight, AnimalSex sex) {
        double maxWeight = (sex.equals(AnimalSex.MALE)) ? AfricanLionStandards.MALE_MAX_WEIGHT_KG : AfricanLionStandards.FEMALE_MAX_WEIGHT_KG;
        double minWeight = (sex.equals(AnimalSex.MALE)) ? AfricanLionStandards.MALE_MIN_WEIGHT_KG : AfricanLionStandards.FEMALE_MIN_WEIGHT_KG;
        if(weight > maxWeight || weight < minWeight){
            throw new RuntimeException();
        }
    }
}
