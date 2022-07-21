package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import populationcensus.repository.entity.WorkInfo;
import populationcensus.repository.entity.Person;
import populationcensus.repository.repositories.WorkInfoRep;
import populationcensus.repository.repositories.PersonRep;
import populationcensus.service.interfaces.WorkInfoService;

import java.util.Optional;

@Service("workInfoService")
@AllArgsConstructor
public class WorkInfoServiceImpl implements WorkInfoService {

    private final WorkInfoRep workInfoRep;
    private final PersonRep personRep;

    @Override
    public void saveWorkInfo(WorkInfo entity) {
        if (entity.getPersonInWorkInfo() == null) {
            return;
        }
        workInfoRep.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void saveWorkInfo(long personId, WorkInfo entity) {
        if (entity.getPersonInWorkInfo() != null) {
            return;
        }
        Optional<Person> personOptional = personRep.findById(personId);

        personOptional.ifPresent(
                (obj) -> {
                    if (obj.getWorkInfo() != null) {
                        return;
                    }
                    entity.setPersonInWorkInfo(personOptional.get());
                    workInfoRep.saveAndFlush(entity);
                }
        );
    }

    @Override
    public void saveWorkInfo(Person person, WorkInfo entity) {
        if (entity.getPersonInWorkInfo() != null || person.getId() == null) {
            return;
        }
        entity.setPersonInWorkInfo(person);
        workInfoRep.saveAndFlush(entity);
    }


    @Override
    public WorkInfo findWorkInfo(long workInfoId) {
        Optional<WorkInfo> result = workInfoRep.findById(workInfoId);
        return result.orElse(null);
    }

    @Override
    @Transactional
    public WorkInfo findWorkInfoByPersonId(long personId) {
        Person person = personRep.findById(personId).orElse(null);
        return workInfoRep.findByPersonInWorkInfo(person).orElse(null);
    }

    @Override
    public WorkInfo findWorkInfoByPerson(Person person) {
        return workInfoRep.findByPersonInWorkInfo(person).orElse(null);
    }


    @Override
    public void deleteWorkInfoById(long workInfoId) {
        workInfoRep.deleteById(workInfoId);
    }

    @Override
    public void deleteWorkInfo(WorkInfo workInfo) {
        workInfoRep.delete(workInfo);
    }

    @Override
    public void deleteWorkInfoByPersonId(long personId) {
        Person person = personRep.findById(personId).orElse(null);
        workInfoRep.deleteByPersonInWorkInfo(person);
    }

    @Override
    public void deleteWorkInfoByPerson(Person person) {
        workInfoRep.deleteByPersonInWorkInfo(person);
    }


    @Transactional
    @Override
    public Person findLinkedPerson(long workInfoId) {
        WorkInfo workInfo = workInfoRep.findById(workInfoId).orElse(new WorkInfo());
        return personRep.findByWorkInfo(workInfo).orElse(null);
    }

    @Override
    public Person findLinkedPerson(WorkInfo workInfo) {
        return personRep.findByWorkInfo(workInfo).orElse(null);
    }
}
