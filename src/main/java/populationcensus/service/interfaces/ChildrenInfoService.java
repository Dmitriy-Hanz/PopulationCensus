package populationcensus.service.interfaces;

import populationcensus.repository.entity.AccommodationsInfo;
import populationcensus.repository.entity.ChildrenInfo;
import populationcensus.repository.entity.Household;
import populationcensus.repository.entity.Person;

public interface ChildrenInfoService {

    void saveChildrenInfo(ChildrenInfo entity);
    void saveChildrenInfo(long personId, ChildrenInfo entity);
    void saveChildrenInfo(Person person, ChildrenInfo entity);

    ChildrenInfo findChildrenInfo(long childrenInfoId);
    ChildrenInfo findChildrenInfoByPersonId(long personId);
    ChildrenInfo findChildrenInfoByPerson(Person person);

    void deleteChildrenInfoById(long childrenInfoId);
    void deleteChildrenInfo(ChildrenInfo childrenInfo);
    void deleteChildrenInfoByPersonId(long personId);
    void deleteChildrenInfoByPerson(Person person);

    Person findLinkedPerson(long childrenInfoId);
    Person findLinkedPerson(ChildrenInfo childrenInfo);

    void updateChildrenInfo(ChildrenInfo entity);
}
