package populationcensus.service.interfaces;

import populationcensus.repository.entity.ChildrenInfo;
import populationcensus.repository.entity.EducationInfo;
import populationcensus.repository.entity.LivingPlaceInfo;
import populationcensus.repository.entity.Person;

public interface LivingPlaceInfoService {

    void saveLivingPlaceInfo(LivingPlaceInfo entity);
    void saveLivingPlaceInfo(long personId, LivingPlaceInfo entity);

    LivingPlaceInfo findLivingPlaceInfo(long livingPlaceInfoId);
    LivingPlaceInfo findLivingPlaceInfoByPerson(Person person);
    LivingPlaceInfo findLivingPlaceInfoByPersonId(long personId);

    void deleteLivingPlaceInfo(LivingPlaceInfo livingPlaceInfo);
    void deleteLivingPlaceInfoById(long livingPlaceInfoId);
    void deleteLivingPlaceInfoByPerson(Person person);
    void deleteLivingPlaceInfoByPersonId(long personId);

    void updateLivingPlaceInfo(LivingPlaceInfo entity);

    Person findLinkedPerson(long livingPlaceInfoId);
}
