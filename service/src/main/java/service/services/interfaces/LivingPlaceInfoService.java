package service.services.interfaces;

import repository.entity.LivingCountryInfo;
import repository.entity.LivingPlaceInfo;
import repository.entity.Person;

public interface LivingPlaceInfoService {

    void createLivingPlaceInfo(int personId);

    void deleteLivingPlaceInfo(int livingPlaceInfoId);
    void deleteLivingPlaceInfoByPerson(int personId);

    void updateLivingPlaceInfo(LivingPlaceInfo entity);

    Person findLinkedPerson(int livingPlaceInfoId);
}
