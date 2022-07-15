package populationcensus.service.interfaces;

import populationcensus.repository.entity.*;

public interface LivingCountryInfoService {

    void saveLivingCountryInfo(LivingCountryInfo entity);
    void saveLivingCountryInfo(long personId, LivingCountryInfo entity);
    void saveLivingCountryInfo(Person person, LivingCountryInfo entity);

    LivingCountryInfo findLivingCountryInfo(long livingCountryInfoId);
    LivingCountryInfo findLivingCountryInfoByPersonId(long personId);
    LivingCountryInfo findLivingCountryInfoByPerson(Person person);

    void deleteLivingCountryInfoById(long livingCountryInfoId);
    void deleteLivingCountryInfo(LivingCountryInfo livingCountryInfo);
    void deleteLivingCountryInfoByPersonId(long personId);
    void deleteLivingCountryInfoByPerson(Person person);

    Person findLinkedPerson(long livingCountryInfoId);
    Person findLinkedPerson(LivingCountryInfo livingCountryInfo);

    void updateLivingCountryInfo(LivingCountryInfo entity);
}
