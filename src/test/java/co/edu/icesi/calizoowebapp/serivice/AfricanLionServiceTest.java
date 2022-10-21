package co.edu.icesi.calizoowebapp.serivice;

import co.edu.icesi.calizoowebapp.constants.AfricanLionErrorCode;
import co.edu.icesi.calizoowebapp.constants.AnimalSex;
import co.edu.icesi.calizoowebapp.error.exception.AfricanLionError;
import co.edu.icesi.calizoowebapp.error.exception.AfricanLionException;
import co.edu.icesi.calizoowebapp.model.AfricanLion;
import co.edu.icesi.calizoowebapp.model.AfricanLionQueryResponse;
import co.edu.icesi.calizoowebapp.repository.AfricanLionRespository;
import co.edu.icesi.calizoowebapp.service.AfricanLionService;
import co.edu.icesi.calizoowebapp.service.impl.AfricanLionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class AfricanLionServiceTest {

    private AfricanLionRespository africanLionRespository;
    private AfricanLionService africanLionService;


    @BeforeEach
    public void init(){
        africanLionRespository = mock(AfricanLionRespository.class);
        africanLionService = new AfricanLionServiceImpl(africanLionRespository);
    }

    private ArrayList<AfricanLion> setUpScenarioNewLionsList(){

        ArrayList<AfricanLion> currentAfricanLionList;
        String lionId = "69a91b05-4621-4a79-9c94-372fa108d132";
        String lionName = "lionFather";
        AnimalSex lionSex = AnimalSex.MALE;
        double lionWeight = 240;
        int lionAge = 5;
        double lionHeight = 110;
        String lionArrivedZooDate = "2020-09-10T08:10:59";
        UUID lionFatherId = null;
        UUID lionMotherId = null;
        AfricanLion fatherAfricanLion =  new AfricanLion(UUID.fromString(lionId), lionName, lionSex, lionWeight, lionAge, lionHeight, LocalDateTime.parse(lionArrivedZooDate), lionFatherId, lionMotherId);

        lionId = "72786b9d-5485-4512-995c-b658e098f46a";
        lionName = "lionMother";
        lionSex = AnimalSex.FEMALE;
        lionWeight = 170;
        lionAge = 2;
        lionHeight = 100;
        lionArrivedZooDate = "2021-10-30T10:45:50";
        AfricanLion motherAfricanLion =  new AfricanLion(UUID.fromString(lionId), lionName, lionSex, lionWeight, lionAge, lionHeight, LocalDateTime.parse(lionArrivedZooDate), lionFatherId, lionMotherId);

        currentAfricanLionList = new ArrayList<>();
        currentAfricanLionList.add(fatherAfricanLion);
        currentAfricanLionList.add(motherAfricanLion);

        return currentAfricanLionList;
    }


    @Test
    public void testCreateLionWithoutParents(){

        String lionId = "0a26da1a-6673-4b7e-9b12-84fa6fe0f25a";
        String lionName = "lionfather";
        AnimalSex lionSex = AnimalSex.MALE;
        double lionWeight = 240;
        int lionAge = 5;
        double lionHeight = 110;
        String lionArrivedZooDate = "2020-09-20T10:10:10";
        UUID lionFatherId = null;
        UUID lionMotherId = null;
        AfricanLion newAfricanLion = new AfricanLion(UUID.fromString(lionId), lionName, lionSex, lionWeight, lionAge, lionHeight, LocalDateTime.parse(lionArrivedZooDate), lionFatherId, lionMotherId);

        when(africanLionRespository.save(newAfricanLion)).thenReturn(newAfricanLion);
        when(africanLionRespository.findAll()).thenReturn(new ArrayList<AfricanLion>());
        africanLionService.createLion(newAfricanLion);
        verify(africanLionRespository, times(1)).save(newAfricanLion);
    }

    @Test
    public void testCreateLionWithParents(){
        ArrayList<AfricanLion> currentAfricanLionList = setUpScenarioNewLionsList();
        AfricanLion newAfricanLion = new AfricanLion(UUID.fromString("aaa6ad08-1dc0-4015-9e21-614afd01b2c9"),
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
    public void testCreateLionRepeatedName(){
        String lionId = "0a26da1a-6673-4b7e-9b12-84fa6fe0f25a";
        String lionName = "lionfather";
        AnimalSex lionSex = AnimalSex.MALE;
        double lionWeight = 240;
        int lionAge = 5;
        double lionHeight = 110;
        String lionArrivedZooDate = "2020-09-20T10:10:10";
        UUID lionFatherId = null;
        UUID lionMotherId = null;
        AfricanLion repeatedAfricanLion = new AfricanLion(UUID.fromString(lionId), lionName, lionSex, lionWeight, lionAge, lionHeight, LocalDateTime.parse(lionArrivedZooDate), lionFatherId, lionMotherId);

        ArrayList<AfricanLion> africanLionList = setUpScenarioNewLionsList();

        when(africanLionRespository.findAll()).thenReturn(africanLionList);
        try {
            africanLionService.createLion(repeatedAfricanLion);
            fail();
        } catch (AfricanLionException africanLionException){
            verify(africanLionRespository, times(0)).save(repeatedAfricanLion);
            assertEquals(africanLionException.getHttpStatus(), HttpStatus.BAD_REQUEST);
            assertEquals(africanLionException.getError().getCode(), AfricanLionErrorCode.CODE_02);
            assertEquals(africanLionException.getError().getMessage(), AfricanLionErrorCode.CODE_02.getMessage());
        }
    }

    @Test
    public void testCreateLionOutOfStandardMaleWeight(){
        String lionId = "0a26da1a-6673-4b7e-9b12-84fa6fe0f25a";
        String lionName = "lionOutStandard";
        AnimalSex lionSex = AnimalSex.MALE;
        double lionWeight = 260;
        int lionAge = 5;
        double lionHeight = 110;
        String lionArrivedZooDate = "2020-09-20T10:10:10";
        UUID lionFatherId = null;
        UUID lionMotherId = null;
        AfricanLion outStandardsAfricanLion = new AfricanLion(UUID.fromString(lionId), lionName, lionSex, lionWeight, lionAge, lionHeight, LocalDateTime.parse(lionArrivedZooDate), lionFatherId, lionMotherId);

        ArrayList<AfricanLion> africanLionList = setUpScenarioNewLionsList();
        when(africanLionRespository.findAll()).thenReturn(africanLionList);

        try {
            africanLionService.createLion(outStandardsAfricanLion);
            fail();
        } catch (AfricanLionException africanLionException){
            verify(africanLionRespository, times(0)).save(outStandardsAfricanLion);
            assertEquals(africanLionException.getHttpStatus(), HttpStatus.BAD_REQUEST);
            assertEquals(africanLionException.getError().getCode(), AfricanLionErrorCode.CODE_06);
            assertEquals(africanLionException.getError().getMessage(), AfricanLionErrorCode.CODE_06.getMessage());
        }
    }

    @Test
    public void testCreateLionOutOfStandardFemaleWeight(){
        String lionId = "0a26da1a-6673-4b7e-9b12-84fa6fe0f25a";
        String lionName = "lionOutStandard";
        AnimalSex lionSex = AnimalSex.FEMALE;
        double lionWeight = 200;
        int lionAge = 5;
        double lionHeight = 110;
        String lionArrivedZooDate = "2020-09-20T10:10:10";
        UUID lionFatherId = null;
        UUID lionMotherId = null;
        AfricanLion outStandardsAfricanLion = new AfricanLion(UUID.fromString(lionId), lionName, lionSex, lionWeight, lionAge, lionHeight, LocalDateTime.parse(lionArrivedZooDate), lionFatherId, lionMotherId);

        ArrayList<AfricanLion> africanLionList = setUpScenarioNewLionsList();
        when(africanLionRespository.findAll()).thenReturn(africanLionList);

        try {
            africanLionService.createLion(outStandardsAfricanLion);
            fail();
        } catch (AfricanLionException africanLionException){
            verify(africanLionRespository, times(0)).save(outStandardsAfricanLion);
            assertEquals(africanLionException.getHttpStatus(), HttpStatus.BAD_REQUEST);
            assertEquals(africanLionException.getError().getCode(), AfricanLionErrorCode.CODE_06);
            assertEquals(africanLionException.getError().getMessage(), AfricanLionErrorCode.CODE_06.getMessage());
        }
    }

    @Test
    public void testCreateLionOutOfStandardMaleHeight(){
        String lionId = "0a26da1a-6673-4b7e-9b12-84fa6fe0f25a";
        String lionName = "lionOutStandard";
        AnimalSex lionSex = AnimalSex.MALE;
        double lionWeight = 240;
        int lionAge = 5;
        double lionHeight = 160;
        String lionArrivedZooDate = "2020-09-20T10:10:10";
        UUID lionFatherId = null;
        UUID lionMotherId = null;
        AfricanLion outStandardsAfricanLion = new AfricanLion(UUID.fromString(lionId), lionName, lionSex, lionWeight, lionAge, lionHeight, LocalDateTime.parse(lionArrivedZooDate), lionFatherId, lionMotherId);

        ArrayList<AfricanLion> africanLionList = setUpScenarioNewLionsList();
        when(africanLionRespository.findAll()).thenReturn(africanLionList);

        try {
            africanLionService.createLion(outStandardsAfricanLion);
            fail();
        } catch (AfricanLionException africanLionException){
            verify(africanLionRespository, times(0)).save(outStandardsAfricanLion);
            assertEquals(africanLionException.getHttpStatus(), HttpStatus.BAD_REQUEST);
            assertEquals(africanLionException.getError().getCode(), AfricanLionErrorCode.CODE_04);
            assertEquals(africanLionException.getError().getMessage(), AfricanLionErrorCode.CODE_04.getMessage());
        }
    }

    @Test
    public void testCreateLionOutOfStandardFemaleHeight(){
        String lionId = "0a26da1a-6673-4b7e-9b12-84fa6fe0f25a";
        String lionName = "lionOutStandard";
        AnimalSex lionSex = AnimalSex.FEMALE;
        double lionWeight = 160;
        int lionAge = 5;
        double lionHeight = 120;
        String lionArrivedZooDate = "2020-09-20T10:10:10";
        UUID lionFatherId = null;
        UUID lionMotherId = null;
        AfricanLion outStandardsAfricanLion = new AfricanLion(UUID.fromString(lionId), lionName, lionSex, lionWeight, lionAge, lionHeight, LocalDateTime.parse(lionArrivedZooDate), lionFatherId, lionMotherId);

        ArrayList<AfricanLion> africanLionList = setUpScenarioNewLionsList();
        when(africanLionRespository.findAll()).thenReturn(africanLionList);

        try {
            africanLionService.createLion(outStandardsAfricanLion);
            fail();
        } catch (AfricanLionException africanLionException){
            verify(africanLionRespository, times(0)).save(outStandardsAfricanLion);
            assertEquals(africanLionException.getHttpStatus(), HttpStatus.BAD_REQUEST);
            assertEquals(africanLionException.getError().getCode(), AfricanLionErrorCode.CODE_04);
            assertEquals(africanLionException.getError().getMessage(), AfricanLionErrorCode.CODE_04.getMessage());
        }
    }

    @Test
    public void testCreateLionOutOfStandardMaleAge(){
        String lionId = "0a26da1a-6673-4b7e-9b12-84fa6fe0f25a";
        String lionName = "lionOutStandard";
        AnimalSex lionSex = AnimalSex.MALE;
        double lionWeight = 240;
        int lionAge = 20;
        double lionHeight = 110;
        String lionArrivedZooDate = "2020-09-20T10:10:10";
        UUID lionFatherId = null;
        UUID lionMotherId = null;
        AfricanLion outStandardsAfricanLion = new AfricanLion(UUID.fromString(lionId), lionName, lionSex, lionWeight, lionAge, lionHeight, LocalDateTime.parse(lionArrivedZooDate), lionFatherId, lionMotherId);

        ArrayList<AfricanLion> africanLionList = setUpScenarioNewLionsList();
        when(africanLionRespository.findAll()).thenReturn(africanLionList);

        try {
            africanLionService.createLion(outStandardsAfricanLion);
            fail();
        } catch (AfricanLionException africanLionException){
            verify(africanLionRespository, times(0)).save(outStandardsAfricanLion);
            assertEquals(africanLionException.getHttpStatus(), HttpStatus.BAD_REQUEST);
            assertEquals(africanLionException.getError().getCode(), AfricanLionErrorCode.CODE_05);
            assertEquals(africanLionException.getError().getMessage(), AfricanLionErrorCode.CODE_05.getMessage());
        }
    }

    @Test
    public void testCreateLionOutOfStandardFemaleAge(){
        String lionId = "0a26da1a-6673-4b7e-9b12-84fa6fe0f25a";
        String lionName = "lionOutStandard";
        AnimalSex lionSex = AnimalSex.FEMALE;
        double lionWeight = 160;
        int lionAge = 16;
        double lionHeight = 100;
        String lionArrivedZooDate = "2020-09-20T10:10:10";
        UUID lionFatherId = null;
        UUID lionMotherId = null;
        AfricanLion outStandardsAfricanLion = new AfricanLion(UUID.fromString(lionId), lionName, lionSex, lionWeight, lionAge, lionHeight, LocalDateTime.parse(lionArrivedZooDate), lionFatherId, lionMotherId);

        ArrayList<AfricanLion> africanLionList = setUpScenarioNewLionsList();
        when(africanLionRespository.findAll()).thenReturn(africanLionList);

        try {
            africanLionService.createLion(outStandardsAfricanLion);
            fail();
        } catch (AfricanLionException africanLionException){
            verify(africanLionRespository, times(0)).save(outStandardsAfricanLion);
            assertEquals(africanLionException.getHttpStatus(), HttpStatus.BAD_REQUEST);
            assertEquals(africanLionException.getError().getCode(), AfricanLionErrorCode.CODE_05);
            assertEquals(africanLionException.getError().getMessage(), AfricanLionErrorCode.CODE_05.getMessage());
        }
    }


    @Test
    public void testGetLion(){
        ArrayList<AfricanLion> currentAfricanLionList = setUpScenarioNewLionsList();
        AfricanLion requestedLion = currentAfricanLionList.get(0);
        String lionName = requestedLion.getName();

        when(africanLionRespository.findAll()).thenReturn(currentAfricanLionList);
        AfricanLionQueryResponse africanLion = africanLionService.getLion(lionName);
        assertEquals(africanLion.getRequestedLion(), requestedLion);
        verify(africanLionRespository, times(1)).findAll();
    }

    @Test
    public void testGetLions(){
        ArrayList<AfricanLion> currentAfricanLionList = setUpScenarioNewLionsList();
        when(africanLionRespository.findAll()).thenReturn(currentAfricanLionList);
        africanLionService.getLions();
        verify(africanLionRespository,times(1)).findAll();
    }

}
