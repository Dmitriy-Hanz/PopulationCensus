package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import populationcensus.repository.entity.EducationInfo;
import populationcensus.repository.entity.Person;
import populationcensus.repository.repositories.EducationInfoRep;
import populationcensus.repository.repositories.PersonRep;
import populationcensus.service.interfaces.EducationInfoService;

import java.util.Optional;

@Service("educationInfoService")
@AllArgsConstructor
public class EducationInfoServiceImpl implements EducationInfoService {

    private final EducationInfoRep educationInfoRep;
    private final PersonRep personRep;

    @Override
    public void saveEducationInfo(EducationInfo entity) {
        if(entity.getPersonInEducationInfo() == null) {
            return;
        }
        educationInfoRep.saveAndFlush(entity);
    }

    @Override
    public void saveEducationInfo(long personId, EducationInfo entity) {
        if (entity.getPersonInEducationInfo() != null) {
            return;
        }
        Optional<Person> personOptional = personRep.findById(personId);

        personOptional.ifPresent(
                (obj) -> {
                    if(obj.getEducationInfo() != null){
                        return;
                    }
                    entity.setPersonInEducationInfo(personOptional.get());
                    educationInfoRep.saveAndFlush(entity);
                }
        );
    }

    @Override
    public void saveEducationInfo(Person person, EducationInfo entity) {
        if(entity.getPersonInEducationInfo() != null || person.getId() == null){
            return;
        }
        entity.setPersonInEducationInfo(person);
        educationInfoRep.saveAndFlush(entity);
    }

    @Override
    public EducationInfo findEducationInfo(long educationInfoId) {
        Optional<EducationInfo> result = educationInfoRep.findById(educationInfoId);
        return result.orElse(null);
    }

    @Override
    public EducationInfo findEducationInfoByPersonId(long personId) {
        Optional<Person> personOptional = personRep.findById(personId);
        Person personResult = personOptional.orElse(null);
        if (personResult == null){
            return null;
        }
        return personResult.getEducationInfo();
    }

    @Override
    public EducationInfo findEducationInfoByPerson(Person person) {
        if (person == null) {
            return null;
        }
        return findEducationInfoByPersonId(person.getId());
    }

    @Override
    public void deleteEducationInfoById(long educationInfoId) {
        educationInfoRep.deleteById(educationInfoId);
        educationInfoRep.flush();
    }

    @Override
    public void deleteEducationInfo(EducationInfo educationInfo) {
        if (educationInfo == null){
            return;
        }
        deleteEducationInfoById(educationInfo.getId());
    }

    @Override
    public void deleteEducationInfoByPersonId(long personId) {
        Optional<Person> personOptional = personRep.findById(personId);
        Person personResult = personOptional.orElse(null);
        if (personResult == null){
            return;
        }
        deleteEducationInfo(personResult.getEducationInfo());
        personRep.flush();
    }

    @Override
    public void deleteEducationInfoByPerson(Person person) {
        if(person == null){
            return;
        }
        deleteEducationInfoByPersonId(person.getId());
    }

    @Override
    public Person findLinkedPerson(long educationInfoId) {
        Optional<EducationInfo> educationInfoOptional = educationInfoRep.findById(educationInfoId);
        EducationInfo educationInfoResult = educationInfoOptional.orElse(null);
        if (educationInfoResult == null){
            return null;
        }
        return educationInfoResult.getPersonInEducationInfo();
    }

    @Override
    public Person findLinkedPerson(EducationInfo educationInfo) {
        return findLinkedPerson(educationInfo.getId());
    }

    @Override
    public void updateEducationInfo(EducationInfo entity) {
        if (entity == null){
            return;
        }
        educationInfoRep.saveAndFlush(entity);
    }
}
