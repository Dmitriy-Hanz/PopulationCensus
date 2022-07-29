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
import populationcensus.repository.entity.LivingPlaceInfo;
import populationcensus.repository.entity.Person;
import populationcensus.repository.repositories.LivingPlaceInfoRep;
import populationcensus.repository.repositories.PersonRep;
import populationcensus.service.impl.LivingPlaceInfoServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:applicationTest.yml")
public class LivingPlaceInfoServiceTest {
    @Mock
    private LivingPlaceInfoRep livingPlaceInfoRep;
    @Mock
    private PersonRep personRep;

    @InjectMocks
    private LivingPlaceInfoServiceImpl livingPlaceInfoService;

    private Person person;
    private LivingPlaceInfo livingPlaceInfo;

    @Before
    public void presets(){
        person = Person.builder()
                .id(Consts.Test.PERSON_ID)
                .age(Consts.Test.PERSON_AGE)
                .name(Consts.Test.PERSON_NAME)
                .build();

        livingPlaceInfo = LivingPlaceInfo.builder()
                .id(Consts.Test.LIVING_PLACE_INFO_ID)
                .isItVillage(Consts.Test.LIVING_PLACE_INFO_IS_IT_VILLAGE)
                .personInLivingPlaceInfo(person)
                .build();

        person.setLivingPlaceInfo(livingPlaceInfo);
    }

    @Test
    public void saveLivingPlaceInfoWithEntityTest() {
        try {
            livingPlaceInfoService.saveLivingPlaceInfo(livingPlaceInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void saveLivingPlaceInfoWithIdAndEntityTest() {
        try {
            livingPlaceInfoService.saveLivingPlaceInfo(person.getId(),livingPlaceInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void saveLivingPlaceInfoWithEntityAndEntityTest() {
        try {
            livingPlaceInfoService.saveLivingPlaceInfo(person,livingPlaceInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }


    @Test
    public void findLivingPlaceInfoByIdTest() {
        when(livingPlaceInfoRep.findById(any())).thenReturn(Optional.of(livingPlaceInfo));

        Optional<LivingPlaceInfo> result = Optional.of(livingPlaceInfoService.findLivingPlaceInfo(livingPlaceInfo.getId()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),livingPlaceInfo);
    }

    @Test
    public void findLivingPlaceInfoByPersonIdTest() {
        when(livingPlaceInfoRep.findByPersonInLivingPlaceInfo(any())).thenReturn(Optional.of(livingPlaceInfo));

        Optional<LivingPlaceInfo> result = Optional.of(livingPlaceInfoService.findLivingPlaceInfoByPersonId(livingPlaceInfo.getPersonInLivingPlaceInfo().getId()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),livingPlaceInfo);
    }

    @Test
    public void findLivingPlaceInfoByPersonTest() {
        when(livingPlaceInfoRep.findByPersonInLivingPlaceInfo(any())).thenReturn(Optional.of(livingPlaceInfo));

        Optional<LivingPlaceInfo> result = Optional.of(livingPlaceInfoService.findLivingPlaceInfoByPerson(livingPlaceInfo.getPersonInLivingPlaceInfo()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),livingPlaceInfo);
    }


    @Test
    public void deleteLivingPlaceInfoByIdTest() {
        try {
            livingPlaceInfoService.deleteLivingPlaceInfoById(livingPlaceInfo.getId());
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteLivingPlaceInfoByEntityTest() {
        try {
            livingPlaceInfoService.deleteLivingPlaceInfo(livingPlaceInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteLivingPlaceInfoByPersonIdTest() {
        try {
            livingPlaceInfoService.deleteLivingPlaceInfoByPersonId(livingPlaceInfo.getPersonInLivingPlaceInfo().getId());
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteLivingPlaceInfoByPersonTest() {
        try {
            livingPlaceInfoService.deleteLivingPlaceInfoByPerson(livingPlaceInfo.getPersonInLivingPlaceInfo());
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }


    @Test
    public void findLinkedPersonByIdTest() {
        when(personRep.findByLivingPlaceInfo(any())).thenReturn(Optional.of(person));

        Optional<Person> result = Optional.of(livingPlaceInfoService.findLinkedPerson(livingPlaceInfo.getId()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),person);
    }

    @Test
    public void findLinkedPersonByEntityTest() {
        when(personRep.findByLivingPlaceInfo(any())).thenReturn(Optional.of(person));

        Optional<Person> result = Optional.of(livingPlaceInfoService.findLinkedPerson(livingPlaceInfo));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),person);
    }
}
