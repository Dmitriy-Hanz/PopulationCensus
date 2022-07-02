package populationcensus.service.interfaces;

import populationcensus.repository.entity.*;

public interface LivingCountryInfoService {

    void saveLivingCountryInfo(LivingCountryInfo entity);
    void saveLivingCountryInfo(long personId, LivingCountryInfo entity);

    LivingCountryInfo findLivingCountryInfo(long livingCountryInfoId);
    LivingCountryInfo findLivingCountryInfoByPerson(Person person);
    LivingCountryInfo findLivingCountryInfoByPersonId(long personId);

    void deleteLivingCountryInfo(LivingCountryInfo livingCountryInfo);
    void deleteLivingCountryInfoById(long livingCountryInfoId);
    void deleteLivingCountryInfoByPerson(Person person);
    void deleteLivingCountryInfoByPersonId(long personId);

    void updateLivingCountryInfo(LivingCountryInfo entity);

    Person findLinkedPerson(int livingCountryInfoId);
}
