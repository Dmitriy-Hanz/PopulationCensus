package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import populationcensus.repository.entity.ChildrenInfo;
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
    @Transactional
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
    @Transactional
    public ChildrenInfo findChildrenInfoByPersonId(long personId) {
        Person person = personRep.findById(personId).orElse(null);
        return childrenInfoRep.findByPersonInChildrenInfo(person).orElse(null);
    }

    @Override
    public ChildrenInfo findChildrenInfoByPerson(Person person) {
        return childrenInfoRep.findByPersonInChildrenInfo(person).orElse(null);
    }


    @Override
    public void deleteChildrenInfoById(long childrenInfoId) {
        childrenInfoRep.deleteById(childrenInfoId);
    }

    @Override
    public void deleteChildrenInfo(ChildrenInfo childrenInfo) {
        childrenInfoRep.delete(childrenInfo);
    }

    @Override
    public void deleteChildrenInfoByPersonId(long personId) {
        Person person = personRep.findById(personId).orElse(null);
        childrenInfoRep.deleteByPersonInChildrenInfo(person);
    }

    @Override
    public void deleteChildrenInfoByPerson(Person person) {
        childrenInfoRep.deleteByPersonInChildrenInfo(person);
    }


    @Transactional
    @Override
    public Person findLinkedPerson(long childrenInfoId) {
        ChildrenInfo childrenInfo = childrenInfoRep.findById(childrenInfoId).orElse(new ChildrenInfo());
        return personRep.findByChildrenInfo(childrenInfo).orElse(null);
    }

    @Override
    public Person findLinkedPerson(ChildrenInfo childrenInfo) {
        return personRep.findByChildrenInfo(childrenInfo).orElse(null);
    }
}
