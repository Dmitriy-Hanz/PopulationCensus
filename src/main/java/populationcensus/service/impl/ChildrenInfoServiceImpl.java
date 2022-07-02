package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import populationcensus.repository.entity.ChildrenInfo;
import populationcensus.repository.entity.Household;
import populationcensus.repository.entity.Person;
import populationcensus.repository.repositories.ChildrenInfoRep;
import populationcensus.service.interfaces.ChildrenInfoService;

@Service("childrenInfoService")
@AllArgsConstructor
public class ChildrenInfoServiceImpl implements ChildrenInfoService {

    private final ChildrenInfoRep childrenInfoRep;

    @Override
    public void saveChildrenInfo(ChildrenInfo entity) {

    }

    @Override
    public void saveChildrenInfo(long personId, ChildrenInfo entity) {

    }

    @Override
    public void saveChildrenInfo(Person person, ChildrenInfo entity) {

    }

    @Override
    public ChildrenInfo findChildrenInfo(long childrenInfoId) {
        return null;
    }

    @Override
    public ChildrenInfo findChildrenInfoByPerson(Person person) {
        return null;
    }

    @Override
    public ChildrenInfo findChildrenInfoByPersonId(long personId) {
        return null;
    }

    @Override
    public void deleteChildrenInfo(ChildrenInfo childrenInfo) {

    }

    @Override
    public void deleteChildrenInfoById(long childrenInfoId) {

    }

    @Override
    public void deleteChildrenInfoByPerson(Household household) {

    }

    @Override
    public void deleteChildrenInfoByPersonId(long householdId) {

    }

    @Override
    public void updateChildrenInfo(ChildrenInfo entity) {

    }

    @Override
    public Person findLinkedPerson(long childrenInfoId) {
        return null;
    }

    @Override
    public Person findLinkedPerson(ChildrenInfo childrenInfo) {
        return null;
    }
}
