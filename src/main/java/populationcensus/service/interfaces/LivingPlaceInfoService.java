package populationcensus.service.interfaces;

import populationcensus.repository.entity.*;

public interface LivingPlaceInfoService {

    void saveLivingPlaceInfo(LivingPlaceInfo entity);
    void saveLivingPlaceInfo(long personId, LivingPlaceInfo entity);
    void saveLivingPlaceInfo(Person person, LivingPlaceInfo entity);

    LivingPlaceInfo findLivingPlaceInfo(long livingPlaceInfoId);
    LivingPlaceInfo findLivingPlaceInfoByPersonId(long personId);
    LivingPlaceInfo findLivingPlaceInfoByPerson(Person person);

    void deleteLivingPlaceInfoById(long livingPlaceInfoId);
    void deleteLivingPlaceInfo(LivingPlaceInfo livingPlaceInfo);
    void deleteLivingPlaceInfoByPersonId(long personId);
    void deleteLivingPlaceInfoByPerson(Person person);

    Person findLinkedPerson(long livingPlaceInfoId);
    Person findLinkedPerson(LivingPlaceInfo livingPlaceInfo);

    void updateLivingPlaceInfo(LivingPlaceInfo entity);
}
