package service.services.interfaces;

import repository.entity.ChildrenInfo;
import repository.entity.Person;
import repository.entity.WorkInfo;

public interface WorkInfoService {

    void createWorkInfo(int personId);

    void deleteWorkInfo(int workInfoId);
    void deleteWorkInfoByPerson(int personId);

    void updateWorkInfo(WorkInfo entity);

    Person findLinkedPerson(int workInfoId);
}
