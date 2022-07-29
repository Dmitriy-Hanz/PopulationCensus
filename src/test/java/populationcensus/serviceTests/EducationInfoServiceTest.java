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
import populationcensus.repository.entity.EducationInfo;
import populationcensus.repository.entity.Person;
import populationcensus.repository.repositories.EducationInfoRep;
import populationcensus.repository.repositories.PersonRep;
import populationcensus.service.impl.EducationInfoServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:applicationTest.yml")
public class EducationInfoServiceTest {
    @Mock
    private EducationInfoRep educationInfoRep;
    @Mock
    private PersonRep personRep;

    @InjectMocks
    private EducationInfoServiceImpl educationInfoService;

    private Person person;
    private EducationInfo educationInfo;

    @Before
    public void presets(){
        person = Person.builder()
                .id(Consts.Test.PERSON_ID)
                .age(Consts.Test.PERSON_AGE)
                .name(Consts.Test.PERSON_NAME)
                .build();

        educationInfo = EducationInfo.builder()
                .id(Consts.Test.EDUCATION_INFO_ID)
                .lvlOfEducation(Consts.Test.EDUCATION_INFO_LVL_OF_EDUCATION)
                .personInEducationInfo(person)
                .build();

        person.setEducationInfo(educationInfo);
    }

    @Test
    public void saveEducationInfoWithEntityTest() {
        try {
            educationInfoService.saveEducationInfo(educationInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void saveEducationInfoWithIdAndEntityTest() {
        try {
            educationInfoService.saveEducationInfo(person.getId(),educationInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void saveEducationInfoWithEntityAndEntityTest() {
        try {
            educationInfoService.saveEducationInfo(person,educationInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }


    @Test
    public void findEducationInfoByIdTest() {
        when(educationInfoRep.findById(any())).thenReturn(Optional.of(educationInfo));

        Optional<EducationInfo> result = Optional.of(educationInfoService.findEducationInfo(educationInfo.getId()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),educationInfo);
    }

    @Test
    public void findEducationInfoByPersonIdTest() {
        when(educationInfoRep.findByPersonInEducationInfo(any())).thenReturn(Optional.of(educationInfo));

        Optional<EducationInfo> result = Optional.of(educationInfoService.findEducationInfoByPersonId(educationInfo.getPersonInEducationInfo().getId()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),educationInfo);
    }

    @Test
    public void findEducationInfoByPersonTest() {
        when(educationInfoRep.findByPersonInEducationInfo(any())).thenReturn(Optional.of(educationInfo));

        Optional<EducationInfo> result = Optional.of(educationInfoService.findEducationInfoByPerson(educationInfo.getPersonInEducationInfo()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),educationInfo);
    }


    @Test
    public void deleteEducationInfoByIdTest() {
        try {
            educationInfoService.deleteEducationInfoById(educationInfo.getId());
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteEducationInfoByEntityTest() {
        try {
            educationInfoService.deleteEducationInfo(educationInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteEducationInfoByPersonIdTest() {
        try {
            educationInfoService.deleteEducationInfoByPersonId(educationInfo.getPersonInEducationInfo().getId());
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteEducationInfoByPersonTest() {
        try {
            educationInfoService.deleteEducationInfoByPerson(educationInfo.getPersonInEducationInfo());
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }


    @Test
    public void findLinkedPersonByIdTest() {
        when(personRep.findByEducationInfo(any())).thenReturn(Optional.of(person));

        Optional<Person> result = Optional.of(educationInfoService.findLinkedPerson(educationInfo.getId()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),person);
    }

    @Test
    public void findLinkedPersonByEntityTest() {
        when(personRep.findByEducationInfo(any())).thenReturn(Optional.of(person));

        Optional<Person> result = Optional.of(educationInfoService.findLinkedPerson(educationInfo));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),person);
    }
}
