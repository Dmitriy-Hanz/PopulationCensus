package populationcensus.serviceTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import populationcensus.Consts;
import populationcensus.repository.entity.AccommodationsInfo;
import populationcensus.repository.entity.Household;
import populationcensus.repository.repositories.AccommodationsInfoRep;
import populationcensus.repository.repositories.HouseholdRep;
import populationcensus.service.impl.AccommodationsInfoServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:applicationTest.yml")
public class AccommodationsInfoServiceTest {

    @Mock
    private AccommodationsInfoRep accommodationsInfoRep;
    @Mock
    private HouseholdRep householdRep;

    @InjectMocks
    private AccommodationsInfoServiceImpl accommodationsInfoService;

    private Household household;
    private AccommodationsInfo accommodationsInfo;

    @Before
    public void presets(){
        household = Household.builder()
                .id(Consts.Test.HOUSEHOLD_ID)
                .roomsCount(Consts.Test.HOUSEHOLD_ROOMS_COUNT)
                .numberOfMembers(Consts.Test.HOUSEHOLD_NUMBER_OF_MEMBERS)
                .build();

        accommodationsInfo = AccommodationsInfo.builder()
                .id(Consts.Test.ACCOMMODATIONS_INFO_ID)
                .areaOfFlat(Consts.Test.ACCOMMODATIONS_INFO_AREA_OF_FLAT)
                .householdInAccommodationsInfo(household)
                .build();

        household.setAccommodationsInfo(accommodationsInfo);
    }

    @Test
    public void saveAccommodationsInfoWithEntityTest() {
        try {
            accommodationsInfoService.saveAccommodationsInfo(accommodationsInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void saveAccommodationsInfoWithIdAndEntityTest() {
        try {
            accommodationsInfoService.saveAccommodationsInfo(household.getId(),accommodationsInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void saveAccommodationsInfoWithEntityAndEntityTest() {
        try {
            accommodationsInfoService.saveAccommodationsInfo(household,accommodationsInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }


    @Test
    public void findAccommodationsInfoByIdTest() {
        when(accommodationsInfoRep.findById(any())).thenReturn(Optional.of(accommodationsInfo));

        Optional<AccommodationsInfo> result = Optional.of(accommodationsInfoService.findAccommodationsInfo(accommodationsInfo.getId()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),accommodationsInfo);
    }

    @Test
    public void findAccommodationsInfoByHouseholdIdTest() {
        when(householdRep.findById(any())).thenReturn(Optional.of(household));

        Optional<AccommodationsInfo> result = Optional.of(accommodationsInfoService.findAccommodationsInfoByHouseholdId(accommodationsInfo.getHouseholdInAccommodationsInfo().getId()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),accommodationsInfo);
    }

    @Test
    public void findAccommodationsInfoByHouseholdTest() {
        when(householdRep.findById(any())).thenReturn(Optional.of(household));

        Optional<AccommodationsInfo> result = Optional.of(accommodationsInfoService.findAccommodationsInfoByHousehold(accommodationsInfo.getHouseholdInAccommodationsInfo()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),accommodationsInfo);
    }


    @Test
    public void deleteAccommodationsInfoByIdTest() {
        try {
            accommodationsInfoService.deleteAccommodationsInfoById(accommodationsInfo.getId());
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteAccommodationsInfoByEntityTest() {
        try {
            accommodationsInfoService.deleteAccommodationsInfo(accommodationsInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteAccommodationsInfoByHouseholdIdTest() {
        try {
            accommodationsInfoService.deleteAccommodationsInfoByHouseholdId(accommodationsInfo.getHouseholdInAccommodationsInfo().getId());
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteAccommodationsInfoByHouseholdTest() {
        try {
            accommodationsInfoService.deleteAccommodationsInfoByHousehold(accommodationsInfo.getHouseholdInAccommodationsInfo());
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }


    @Test
    public void findLinkedHouseholdByIdTest() {
        when(accommodationsInfoRep.findById(any())).thenReturn(Optional.of(accommodationsInfo));

        Optional<Household> result = Optional.of(accommodationsInfoService.findLinkedHousehold(accommodationsInfo.getId()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),household);
    }

    @Test
    public void findLinkedHouseholdByEntityTest() {
        when(accommodationsInfoRep.findById(any())).thenReturn(Optional.of(accommodationsInfo));

        Optional<Household> result = Optional.of(accommodationsInfoService.findLinkedHousehold(accommodationsInfo));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),household);
    }
}
