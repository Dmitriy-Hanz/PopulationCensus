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
import populationcensus.repository.entity.FullAddressInfo;
import populationcensus.repository.entity.Household;
import populationcensus.repository.repositories.FullAddressInfoRep;
import populationcensus.repository.repositories.HouseholdRep;
import populationcensus.service.impl.FullAddressInfoServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:applicationTest.yml")
public class FullAddressInfoServiceTest {

    @Mock
    private FullAddressInfoRep fullAddressInfoRep;
    @Mock
    private HouseholdRep householdRep;

    @InjectMocks
    private FullAddressInfoServiceImpl fullAddressInfoService;

    private Household household;
    private FullAddressInfo fullAddressInfo;

    @Before
    public void presets(){
        household = Household.builder()
                .id(1L)
                .roomsCount(3)
                .numberOfMembers(3)
                .build();

        fullAddressInfo = FullAddressInfo.builder()
                .id(1L)
                .villageName("Village")
                .householdInFullAddressInfo(household)
                .build();

        household.setFullAddressInfo(fullAddressInfo);
    }

    @Test
    public void saveFullAddressInfoWithEntityTest() {
        try {
            fullAddressInfoService.saveFullAddressInfo(fullAddressInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void saveFullAddressInfoWithIdAndEntityTest() {
        try {
            fullAddressInfoService.saveFullAddressInfo(household.getId(),fullAddressInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void saveFullAddressInfoWithEntityAndEntityTest() {
        try {
            fullAddressInfoService.saveFullAddressInfo(household,fullAddressInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }


    @Test
    public void findFullAddressInfoByIdTest() {
        when(fullAddressInfoRep.findById(any())).thenReturn(Optional.of(fullAddressInfo));

        Optional<FullAddressInfo> result = Optional.of(fullAddressInfoService.findFullAddressInfo(fullAddressInfo.getId()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),fullAddressInfo);
    }

    @Test
    public void findFullAddressInfoByHouseholdIdTest() {
        when(householdRep.findById(any())).thenReturn(Optional.of(household));

        Optional<FullAddressInfo> result = Optional.of(fullAddressInfoService.findFullAddressInfoByHouseholdId(fullAddressInfo.getHouseholdInFullAddressInfo().getId()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),fullAddressInfo);
    }

    @Test
    public void findFullAddressInfoByHouseholdTest() {
        when(householdRep.findById(any())).thenReturn(Optional.of(household));

        Optional<FullAddressInfo> result = Optional.of(fullAddressInfoService.findFullAddressInfoByHousehold(fullAddressInfo.getHouseholdInFullAddressInfo()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),fullAddressInfo);
    }


    @Test
    public void deleteFullAddressInfoByIdTest() {
        try {
            fullAddressInfoService.deleteFullAddressInfoById(fullAddressInfo.getId());
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteFullAddressInfoByEntityTest() {
        try {
            fullAddressInfoService.deleteFullAddressInfo(fullAddressInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteFullAddressInfoByHouseholdIdTest() {
        try {
            fullAddressInfoService.deleteFullAddressInfoByHouseholdId(fullAddressInfo.getHouseholdInFullAddressInfo().getId());
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteFullAddressInfoByHouseholdTest() {
        try {
            fullAddressInfoService.deleteFullAddressInfoByHousehold(fullAddressInfo.getHouseholdInFullAddressInfo());
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }


    @Test
    public void findLinkedHouseholdByIdTest() {
        when(fullAddressInfoRep.findById(any())).thenReturn(Optional.of(fullAddressInfo));

        Optional<Household> result = Optional.of(fullAddressInfoService.findLinkedHousehold(fullAddressInfo.getId()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),household);
    }

    @Test
    public void findLinkedHouseholdByEntityTest() {
        when(fullAddressInfoRep.findById(any())).thenReturn(Optional.of(fullAddressInfo));

        Optional<Household> result = Optional.of(fullAddressInfoService.findLinkedHousehold(fullAddressInfo));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),household);
    }
}
