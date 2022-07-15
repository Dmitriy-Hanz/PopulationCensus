package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import populationcensus.repository.entity.WorkInfo;
import populationcensus.repository.entity.Person;
import populationcensus.repository.repositories.PersonRep;
import populationcensus.repository.repositories.WorkInfoRep;
import populationcensus.service.interfaces.WorkInfoService;

import java.util.Optional;

@Service("workInfoService")
@AllArgsConstructor
public class WorkInfoServiceImpl implements WorkInfoService {

    private final WorkInfoRep workInfoRep;
    private final PersonRep personRep;

    @Override
    public void saveWorkInfo(WorkInfo entity) {
        if(entity.getPersonInWorkInfo() == null) {
            return;
        }
        workInfoRep.saveAndFlush(entity);
    }

    @Override
    public void saveWorkInfo(long personId, WorkInfo entity) {
        if (entity.getPersonInWorkInfo() != null) {
            return;
        }
        Optional<Person> personOptional = personRep.findById(personId);

        personOptional.ifPresent(
                (obj) -> {
                    if(obj.getWorkInfo() != null){
                        return;
                    }
                    entity.setPersonInWorkInfo(personOptional.get());
                    workInfoRep.saveAndFlush(entity);
                }
        );
    }

    @Override
    public void saveWorkInfo(Person person, WorkInfo entity) {
        if(entity.getPersonInWorkInfo() != null || person.getId() == null){
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
    public WorkInfo findWorkInfoByPersonId(long personId) {
        Optional<Person> personOptional = personRep.findById(personId);
        Person personResult = personOptional.orElse(null);
        if (personResult == null){
            return null;
        }
        return personResult.getWorkInfo();
    }

    @Override
    public WorkInfo findWorkInfoByPerson(Person person) {
        if (person == null) {
            return null;
        }
        return findWorkInfoByPersonId(person.getId());
    }


    @Override
    public void deleteWorkInfoById(long workInfoId) {
        workInfoRep.deleteById(workInfoId);
        workInfoRep.flush();
    }

    @Override
    public void deleteWorkInfo(WorkInfo workInfo) {
        if (workInfo == null){
            return;
        }
        deleteWorkInfoById(workInfo.getId());
    }

    @Override
    public void deleteWorkInfoByPersonId(long personId) {
        Optional<Person> personOptional = personRep.findById(personId);
        Person personResult = personOptional.orElse(null);
        if (personResult == null){
            return;
        }
        deleteWorkInfo(personResult.getWorkInfo());
        personRep.flush();
    }

    @Override
    public void deleteWorkInfoByPerson(Person person) {
        if(person == null){
            return;
        }
        deleteWorkInfoByPersonId(person.getId());
    }



    @Override
    public Person findLinkedPerson(long workInfoId) {
        Optional<WorkInfo> workInfoOptional = workInfoRep.findById(workInfoId);
        WorkInfo workInfoResult = workInfoOptional.orElse(null);
        if (workInfoResult == null){
            return null;
        }
        return workInfoResult.getPersonInWorkInfo();
    }

    @Override
    public Person findLinkedPerson(WorkInfo workInfo) {
        return findLinkedPerson(workInfo.getId());
    }


    @Override
    public void updateWorkInfo(WorkInfo entity) {
        if (entity == null){
            return;
        }
        workInfoRep.saveAndFlush(entity);
    }
}
