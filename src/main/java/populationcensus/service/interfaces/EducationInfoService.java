package populationcensus.service.interfaces;

import populationcensus.repository.entity.ChildrenInfo;
import populationcensus.repository.entity.EducationInfo;
import populationcensus.repository.entity.Household;
import populationcensus.repository.entity.Person;

public interface EducationInfoService {

    void saveEducationInfo(EducationInfo entity);
    void saveEducationInfo(long personId, EducationInfo entity);
    void saveEducationInfo(Person person, EducationInfo entity);

    EducationInfo findEducationInfo(long educationInfoId);
    EducationInfo findEducationInfoByPersonId(long personId);
    EducationInfo findEducationInfoByPerson(Person person);

    void deleteEducationInfoById(long educationInfoId);
    void deleteEducationInfo(EducationInfo educationInfo);
    void deleteEducationInfoByPersonId(long personId);
    void deleteEducationInfoByPerson(Person person);

    Person findLinkedPerson(long educationInfoId);
    Person findLinkedPerson(EducationInfo educationInfo);

    void updateEducationInfo(EducationInfo entity);

}
