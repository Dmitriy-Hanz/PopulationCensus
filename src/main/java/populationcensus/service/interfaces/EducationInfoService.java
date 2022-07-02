package populationcensus.service.interfaces;

import populationcensus.repository.entity.ChildrenInfo;
import populationcensus.repository.entity.EducationInfo;
import populationcensus.repository.entity.Household;
import populationcensus.repository.entity.Person;

public interface EducationInfoService {

    void saveEducationInfo(EducationInfo entity);
    void saveEducationInfo(long personId, ChildrenInfo entity);

    EducationInfo findEducationInfo(long educationInfoId);
    EducationInfo findEducationInfoByPerson(Person person);
    EducationInfo findEducationInfoByPersonId(long personId);

    void deleteEducationInfo(EducationInfo educationInfo);
    void deleteEducationInfoById(long educationInfoId);
    void deleteEducationInfoByPerson(Person person);
    void deleteEducationInfoByPersonId(long personId);

    void updateEducationInfo(EducationInfo entity);

    Person findLinkedPerson(long educationInfoId);
}
