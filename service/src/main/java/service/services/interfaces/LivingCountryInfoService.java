package service.services.interfaces;

import repository.entity.ChildrenInfo;
import repository.entity.LivingCountryInfo;
import repository.entity.Person;

public interface LivingCountryInfoService {

    void createLivingCountryInfo(int personId);

    void deleteLivingCountryInfo(int livingCountryInfoId);
    void deleteLivingCountryInfoByPerson(int personId);

    void updateLivingCountryInfo(LivingCountryInfo entity);

    Person findLinkedPerson(int livingCountryInfoId);
}
