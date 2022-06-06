package service.services.interfaces;

import repository.entity.ChildrenInfo;
import repository.entity.EducationInfo;
import repository.entity.Person;

public interface EducationInfoService {

    void createEducationInfo(int personId);

    void deleteEducationInfo(int educationInfoId);
    void deleteEducationInfoByPerson(int personId);

    void updateEducationInfo(EducationInfo entity);

    Person findLinkedPerson(int educationInfoId);
}
