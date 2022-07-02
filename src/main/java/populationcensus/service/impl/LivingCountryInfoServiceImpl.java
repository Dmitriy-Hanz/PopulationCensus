package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import populationcensus.repository.entity.LivingCountryInfo;
import populationcensus.repository.entity.Person;
import populationcensus.repository.repositories.LivingCountryInfoRep;
import populationcensus.service.interfaces.LivingCountryInfoService;

@Service("livingCountryInfoService")
@AllArgsConstructor
public class LivingCountryInfoServiceImpl implements LivingCountryInfoService {

    private final LivingCountryInfoRep livingCountryInfoRep;

    @Override
    public void saveLivingCountryInfo(LivingCountryInfo entity) {

    }

    @Override
    public void saveLivingCountryInfo(long personId, LivingCountryInfo entity) {

    }

    @Override
    public LivingCountryInfo findLivingCountryInfo(long livingCountryInfoId) {
        return null;
    }

    @Override
    public LivingCountryInfo findLivingCountryInfoByPerson(Person person) {
        return null;
    }

    @Override
    public LivingCountryInfo findLivingCountryInfoByPersonId(long personId) {
        return null;
    }

    @Override
    public void deleteLivingCountryInfo(LivingCountryInfo livingCountryInfo) {

    }

    @Override
    public void deleteLivingCountryInfoById(long livingCountryInfoId) {

    }

    @Override
    public void deleteLivingCountryInfoByPerson(Person person) {

    }

    @Override
    public void deleteLivingCountryInfoByPersonId(long personId) {

    }

    @Override
    public void updateLivingCountryInfo(LivingCountryInfo entity) {

    }

    @Override
    public Person findLinkedPerson(int livingCountryInfoId) {
        return null;
    }
}
