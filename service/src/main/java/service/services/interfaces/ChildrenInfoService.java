package service.services.interfaces;

import repository.entity.ChildrenInfo;
import repository.entity.Person;

public interface ChildrenInfoService {

    void createChildrenInfo(int personId);

    void deleteChildrenInfo(int childrenInfoId);
    void deleteChildrenInfoByPerson(int personId);

    void updateChildrenInfo(ChildrenInfo entity);

    Person findLinkedPerson(int childrenInfoId);
}
