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
import populationcensus.repository.entity.LivingCountryInfo;
import populationcensus.repository.entity.Person;
import populationcensus.repository.repositories.LivingCountryInfoRep;
import populationcensus.repository.repositories.PersonRep;
import populationcensus.service.impl.LivingCountryInfoServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:applicationTest.yml")
public class LivingCountryInfoServiceTest {
    @Mock
    private LivingCountryInfoRep livingCountryInfoRep;
    @Mock
    private PersonRep personRep;

    @InjectMocks
    private LivingCountryInfoServiceImpl livingCountryInfoService;

    private Person person;
    private LivingCountryInfo livingCountryInfo;

    @Before
    public void presets(){
        person = Person.builder()
                .id(Consts.Test.PERSON_ID)
                .age(Consts.Test.PERSON_AGE)
                .name(Consts.Test.PERSON_NAME)
                .build();

        livingCountryInfo = LivingCountryInfo.builder()
                .id(Consts.Test.LIVING_COUNTRY_INFO_ID)
                .infoAboutLeavingBelarus(Consts.Test.LIVING_COUNTRY_INFO_INFO_ABOUT_LEAVING_BELARUS)
                .personInLivingCountryInfo(person)
                .build();

        person.setLivingCountryInfo(livingCountryInfo);
    }

    @Test
    public void saveLivingCountryInfoWithEntityTest() {
        try {
            livingCountryInfoService.saveLivingCountryInfo(livingCountryInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void saveLivingCountryInfoWithIdAndEntityTest() {
        try {
            livingCountryInfoService.saveLivingCountryInfo(person.getId(),livingCountryInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void saveLivingCountryInfoWithEntityAndEntityTest() {
        try {
            livingCountryInfoService.saveLivingCountryInfo(person,livingCountryInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }


    @Test
    public void findLivingCountryInfoByIdTest() {
        when(livingCountryInfoRep.findById(any())).thenReturn(Optional.of(livingCountryInfo));

        Optional<LivingCountryInfo> result = Optional.of(livingCountryInfoService.findLivingCountryInfo(livingCountryInfo.getId()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),livingCountryInfo);
    }

    @Test
    public void findLivingCountryInfoByPersonIdTest() {
        when(livingCountryInfoRep.findByPersonInLivingCountryInfo(any())).thenReturn(Optional.of(livingCountryInfo));

        Optional<LivingCountryInfo> result = Optional.of(livingCountryInfoService.findLivingCountryInfoByPersonId(livingCountryInfo.getPersonInLivingCountryInfo().getId()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),livingCountryInfo);
    }

    @Test
    public void findLivingCountryInfoByPersonTest() {
        when(livingCountryInfoRep.findByPersonInLivingCountryInfo(any())).thenReturn(Optional.of(livingCountryInfo));

        Optional<LivingCountryInfo> result = Optional.of(livingCountryInfoService.findLivingCountryInfoByPerson(livingCountryInfo.getPersonInLivingCountryInfo()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),livingCountryInfo);
    }


    @Test
    public void deleteLivingCountryInfoByIdTest() {
        try {
            livingCountryInfoService.deleteLivingCountryInfoById(livingCountryInfo.getId());
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteLivingCountryInfoByEntityTest() {
        try {
            livingCountryInfoService.deleteLivingCountryInfo(livingCountryInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteLivingCountryInfoByPersonIdTest() {
        try {
            livingCountryInfoService.deleteLivingCountryInfoByPersonId(livingCountryInfo.getPersonInLivingCountryInfo().getId());
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteLivingCountryInfoByPersonTest() {
        try {
            livingCountryInfoService.deleteLivingCountryInfoByPerson(livingCountryInfo.getPersonInLivingCountryInfo());
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }


    @Test
    public void findLinkedPersonByIdTest() {
        when(personRep.findByLivingCountryInfo(any())).thenReturn(Optional.of(person));

        Optional<Person> result = Optional.of(livingCountryInfoService.findLinkedPerson(livingCountryInfo.getId()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),person);
    }

    @Test
    public void findLinkedPersonByEntityTest() {
        when(personRep.findByLivingCountryInfo(any())).thenReturn(Optional.of(person));

        Optional<Person> result = Optional.of(livingCountryInfoService.findLinkedPerson(livingCountryInfo));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),person);
    }
}
