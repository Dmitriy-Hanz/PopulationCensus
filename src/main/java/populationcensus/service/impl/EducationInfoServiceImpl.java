package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        if (entity.getPersonInEducationInfo() == null) {
            return;
        }
        educationInfoRep.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void saveEducationInfo(long personId, EducationInfo entity) {
        if (entity.getPersonInEducationInfo() != null) {
            return;
        }
        Optional<Person> personOptional = personRep.findById(personId);

        personOptional.ifPresent(
                (obj) -> {
                    if (obj.getEducationInfo() != null) {
                        return;
                    }
                    entity.setPersonInEducationInfo(personOptional.get());
                    educationInfoRep.saveAndFlush(entity);
                }
        );
    }

    @Override
    public void saveEducationInfo(Person person, EducationInfo entity) {
        if (entity.getPersonInEducationInfo() != null || person.getId() == null) {
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
    @Transactional
    public EducationInfo findEducationInfoByPersonId(long personId) {
        Person person = personRep.findById(personId).orElse(null);
        return educationInfoRep.findByPersonInEducationInfo(person).orElse(null);
    }

    @Override
    public EducationInfo findEducationInfoByPerson(Person person) {
        return educationInfoRep.findByPersonInEducationInfo(person).orElse(null);
    }


    @Override
    public void deleteEducationInfoById(long educationInfoId) {
        educationInfoRep.deleteById(educationInfoId);
    }

    @Override
    public void deleteEducationInfo(EducationInfo educationInfo) {
        educationInfoRep.delete(educationInfo);
    }

    @Override
    public void deleteEducationInfoByPersonId(long personId) {
        Person person = personRep.findById(personId).orElse(null);
        educationInfoRep.deleteByPersonInEducationInfo(person);
    }

    @Override
    public void deleteEducationInfoByPerson(Person person) {
        educationInfoRep.deleteByPersonInEducationInfo(person);
    }


    @Transactional
    @Override
    public Person findLinkedPerson(long educationInfoId) {
        EducationInfo educationInfo = educationInfoRep.findById(educationInfoId).orElse(new EducationInfo());
        return personRep.findByEducationInfo(educationInfo).orElse(null);
    }

    @Override
    public Person findLinkedPerson(EducationInfo educationInfo) {
        return personRep.findByEducationInfo(educationInfo).orElse(null);
    }
}
