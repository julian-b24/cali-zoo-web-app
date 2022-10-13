package co.edu.icesi.calizoowebapp.serivice;

import co.edu.icesi.calizoowebapp.constants.AnimalSex;
import co.edu.icesi.calizoowebapp.model.AfricanLion;
import co.edu.icesi.calizoowebapp.repository.AfricanLionRespository;
import co.edu.icesi.calizoowebapp.service.AfricanLionService;
import co.edu.icesi.calizoowebapp.service.impl.AfricanLionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class AfricanLionServiceTest {

    private AfricanLionRespository africanLionRespository;
    private AfricanLionService africanLionService;

    private AfricanLion newAfricanLion;
    private ArrayList<AfricanLion> currentAfricanLionList;

    @BeforeEach
    public void init(){
        africanLionRespository = mock(AfricanLionRespository.class);
        africanLionService = new AfricanLionServiceImpl(africanLionRespository);
    }

    private void setUpScenario1NewLion(){
        String lionId = "0a26da1a-6673-4b7e-9b12-84fa6fe0f25a";
        String lionName = "lionfather";
        AnimalSex lionSex = AnimalSex.MALE;
        double lionWeight = 240;
        int lionAge = 5;
        double lionHeight = 110;
        String lionArrivedZooDate = "2020-09-20T10:10:10";
        UUID lionFatherId = null;
        UUID lionMotherId = null;

        newAfricanLion =  new AfricanLion(UUID.fromString(lionId), lionName, lionSex, lionWeight, lionAge, lionHeight, LocalDateTime.parse(lionArrivedZooDate), lionFatherId, lionMotherId);
    }

    private void setUpScenario2NewLionsList(){
        String lionId = "69a91b05-4621-4a79-9c94-372fa108d132";
        String lionName = "lionfather";
        AnimalSex lionSex = AnimalSex.MALE;
        double lionWeight = 240;
        int lionAge = 5;
        double lionHeight = 110;
        String lionArrivedZooDate = "2020-09-10T08:10:59";
        UUID lionFatherId = null;
        UUID lionMotherId = null;
        AfricanLion fatherAfricanLion =  new AfricanLion(UUID.fromString(lionId), lionName, lionSex, lionWeight, lionAge, lionHeight, LocalDateTime.parse(lionArrivedZooDate), lionFatherId, lionMotherId);

        lionId = "72786b9d-5485-4512-995c-b658e098f46a";
        lionName = "lionmother";
        lionSex = AnimalSex.FEMALE;
        lionWeight = 170;
        lionAge = 2;
        lionHeight = 100;
        lionArrivedZooDate = "2021-10-30T10:45:50";
        lionFatherId = null;
        lionMotherId = null;
        AfricanLion motherAfricanLion =  new AfricanLion(UUID.fromString(lionId), lionName, lionSex, lionWeight, lionAge, lionHeight, LocalDateTime.parse(lionArrivedZooDate), lionFatherId, lionMotherId);

        currentAfricanLionList = new ArrayList<>();
        currentAfricanLionList.add(fatherAfricanLion);
        currentAfricanLionList.add(motherAfricanLion);

    }

    @Test
    public void testCreateLionWithoutParents(){
        setUpScenario1NewLion();
        when(africanLionRespository.save(newAfricanLion)).thenReturn(newAfricanLion);
        when(africanLionRespository.findAll()).thenReturn(new ArrayList<AfricanLion>());
        africanLionService.createLion(newAfricanLion);
        verify(africanLionRespository, times(1)).save(newAfricanLion);
    }

    @Test
    public void testCreateLionWithParents(){
        setUpScenario2NewLionsList();
        newAfricanLion = new AfricanLion(UUID.fromString("aaa6ad08-1dc0-4015-9e21-614afd01b2c9"),
                "lionchild", AnimalSex.MALE, 200, 0, 105,
                LocalDateTime.parse("2021-12-10T08:10:59"),
                UUID.fromString("69a91b05-4621-4a79-9c94-372fa108d132"),
                UUID.fromString("cbe5ea52-0edb-4d2e-a883-1488f1520b20"));

        when(africanLionRespository.save(newAfricanLion)).thenReturn(newAfricanLion);
        when(africanLionRespository.findAll()).thenReturn(new ArrayList<AfricanLion>());
        africanLionService.createLion(newAfricanLion);
        verify(africanLionRespository, times(1)).save(newAfricanLion);
    }

    @Test
    public void testGetLion(){

    }

    @Test
    public void testGetLions(){

    }

}
