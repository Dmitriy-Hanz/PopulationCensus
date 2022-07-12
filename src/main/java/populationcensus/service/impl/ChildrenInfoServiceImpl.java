package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import populationcensus.repository.entity.ChildrenInfo;
import populationcensus.repository.entity.Household;
import populationcensus.repository.entity.Person;
import populationcensus.repository.repositories.ChildrenInfoRep;
import populationcensus.repository.repositories.PersonRep;
import populationcensus.service.interfaces.ChildrenInfoService;

import java.util.Optional;

@Service("childrenInfoService")
@AllArgsConstructor
public class ChildrenInfoServiceImpl implements ChildrenInfoService {

    private final ChildrenInfoRep childrenInfoRep;
    private final PersonRep personRep;

    @Override
    public void saveChildrenInfo(ChildrenInfo entity) {
        if(entity.getPersonInChildrenInfo() == null) {
            return;
        }
        childrenInfoRep.saveAndFlush(entity);
    }

    @Override
    public void saveChildrenInfo(long personId, ChildrenInfo entity) {
        if (entity.getPersonInChildrenInfo() != null) {
            return;
        }
        Optional<Person> personOptional = personRep.findById(personId);

        personOptional.ifPresent(
                (obj) -> {
                    if(obj.getChildrenInfo() != null){
                        return;
                    }
                    entity.setPersonInChildrenInfo(personOptional.get());
                    childrenInfoRep.saveAndFlush(entity);
                }
        );
    }

    @Override
    public void saveChildrenInfo(Person person, ChildrenInfo entity) {
        if(entity.getPersonInChildrenInfo() != null || person.getId() == null){
            return;
        }
        entity.setPersonInChildrenInfo(person);
        childrenInfoRep.saveAndFlush(entity);
    }


    @Override
    public ChildrenInfo findChildrenInfo(long childrenInfoId) {
        Optional<ChildrenInfo> result = childrenInfoRep.findById(childrenInfoId);
        return result.orElse(null);
    }

    @Override
    public ChildrenInfo findChildrenInfoByPersonId(long personId) {
        Optional<Person> personOptional = personRep.findById(personId);
        Person personResult = personOptional.orElse(null);
        if (personResult == null){
            return null;
        }
        return personResult.getChildrenInfo();
    }

    @Override
    public ChildrenInfo findChildrenInfoByPerson(Person person) {
        if (person == null) {
            return null;
        }
        return findChildrenInfoByPersonId(person.getId());
    }


    @Override
    public void deleteChildrenInfoById(long childrenInfo) {
        childrenInfoRep.deleteById(childrenInfo);
        childrenInfoRep.flush();
    }

    @Override
    public void deleteChildrenInfo(ChildrenInfo childrenInfo) {
        if (childrenInfo == null){
            return;
        }
        deleteChildrenInfoById(childrenInfo.getId());
    }

    @Override
    public void deleteChildrenInfoByPersonId(long personId) {
        Optional<Person> personOptional = personRep.findById(personId);
        Person personResult = personOptional.orElse(null);
        if (personResult == null){
            return;
        }
        deleteChildrenInfo(personResult.getChildrenInfo());
        personRep.flush();
    }

    @Override
    public void deleteChildrenInfoByPerson(Person person) {
        if(person == null){
            return;
        }
        deleteChildrenInfoByPersonId(person.getId());
    }



    @Override
    public Person findLinkedPerson(long childrenInfoId) {
        Optional<ChildrenInfo> childrenInfoOptional = childrenInfoRep.findById(childrenInfoId);
        ChildrenInfo childrenInfoResult = childrenInfoOptional.orElse(null);
        if (childrenInfoResult == null){
            return null;
        }
        return childrenInfoResult.getPersonInChildrenInfo();
    }

    @Override
    public Person findLinkedPerson(ChildrenInfo childrenInfo) {
        return findLinkedPerson(childrenInfo.getId());
    }


    @Override
    public void updateChildrenInfo(ChildrenInfo entity) {
        if (entity == null){
            return;
        }
        childrenInfoRep.saveAndFlush(entity);
    }
}
