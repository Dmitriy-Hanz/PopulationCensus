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
import populationcensus.repository.entity.WorkInfo;
import populationcensus.repository.entity.Person;
import populationcensus.repository.repositories.WorkInfoRep;
import populationcensus.repository.repositories.PersonRep;
import populationcensus.service.impl.WorkInfoServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:applicationTest.yml")
public class WorkInfoServiceTest {
    @Mock
    private WorkInfoRep workInfoRep;
    @Mock
    private PersonRep personRep;

    @InjectMocks
    private WorkInfoServiceImpl workInfoService;

    private Person person;
    private WorkInfo workInfo;

    @Before
    public void presets(){
        person = Person.builder()
                .id(Consts.Test.PERSON_ID)
                .age(Consts.Test.PERSON_AGE)
                .name(Consts.Test.PERSON_NAME)
                .build();

        workInfo = WorkInfo.builder()
                .id(Consts.Test.WORK_INFO_ID)
                .typeOfWorkplace(Consts.Test.WORK_INFO_TYPE_OF_WORKPLACE)
                .personInWorkInfo(person)
                .build();

        person.setWorkInfo(workInfo);
    }

    @Test
    public void saveWorkInfoWithEntityTest() {
        try {
            workInfoService.saveWorkInfo(workInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void saveWorkInfoWithIdAndEntityTest() {
        try {
            workInfoService.saveWorkInfo(person.getId(),workInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void saveWorkInfoWithEntityAndEntityTest() {
        try {
            workInfoService.saveWorkInfo(person,workInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }


    @Test
    public void findWorkInfoByIdTest() {
        when(workInfoRep.findById(any())).thenReturn(Optional.of(workInfo));

        Optional<WorkInfo> result = Optional.of(workInfoService.findWorkInfo(workInfo.getId()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),workInfo);
    }

    @Test
    public void findWorkInfoByPersonIdTest() {
        when(workInfoRep.findByPersonInWorkInfo(any())).thenReturn(Optional.of(workInfo));

        Optional<WorkInfo> result = Optional.of(workInfoService.findWorkInfoByPersonId(workInfo.getPersonInWorkInfo().getId()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),workInfo);
    }

    @Test
    public void findWorkInfoByPersonTest() {
        when(workInfoRep.findByPersonInWorkInfo(any())).thenReturn(Optional.of(workInfo));

        Optional<WorkInfo> result = Optional.of(workInfoService.findWorkInfoByPerson(workInfo.getPersonInWorkInfo()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),workInfo);
    }


    @Test
    public void deleteWorkInfoByIdTest() {
        try {
            workInfoService.deleteWorkInfoById(workInfo.getId());
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteWorkInfoByEntityTest() {
        try {
            workInfoService.deleteWorkInfo(workInfo);
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteWorkInfoByPersonIdTest() {
        try {
            workInfoService.deleteWorkInfoByPersonId(workInfo.getPersonInWorkInfo().getId());
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteWorkInfoByPersonTest() {
        try {
            workInfoService.deleteWorkInfoByPerson(workInfo.getPersonInWorkInfo());
        } catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }


    @Test
    public void findLinkedPersonByIdTest() {
        when(personRep.findByWorkInfo(any())).thenReturn(Optional.of(person));

        Optional<Person> result = Optional.of(workInfoService.findLinkedPerson(workInfo.getId()));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),person);
    }

    @Test
    public void findLinkedPersonByEntityTest() {
        when(personRep.findByWorkInfo(any())).thenReturn(Optional.of(person));

        Optional<Person> result = Optional.of(workInfoService.findLinkedPerson(workInfo));

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get(),person);
    }
}

