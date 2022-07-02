package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import populationcensus.repository.entity.LivingPlaceInfo;
import populationcensus.repository.entity.Person;
import populationcensus.repository.repositories.LivingPlaceInfoRep;
import populationcensus.service.interfaces.LivingPlaceInfoService;

@Service("livingPlaceInfoService")
@AllArgsConstructor
public class LivingPlaceInfoServiceImpl implements LivingPlaceInfoService {

    private final LivingPlaceInfoRep livingPlaceInfoRep;

    @Override
    public void saveLivingPlaceInfo(LivingPlaceInfo entity) {

    }

    @Override
    public void saveLivingPlaceInfo(long personId, LivingPlaceInfo entity) {

    }

    @Override
    public LivingPlaceInfo findLivingPlaceInfo(long livingPlaceInfoId) {
        return null;
    }

    @Override
    public LivingPlaceInfo findLivingPlaceInfoByPerson(Person person) {
        return null;
    }

    @Override
    public LivingPlaceInfo findLivingPlaceInfoByPersonId(long personId) {
        return null;
    }

    @Override
    public void deleteLivingPlaceInfo(LivingPlaceInfo livingPlaceInfo) {

    }

    @Override
    public void deleteLivingPlaceInfoById(long livingPlaceInfoId) {

    }

    @Override
    public void deleteLivingPlaceInfoByPerson(Person person) {

    }

    @Override
    public void deleteLivingPlaceInfoByPersonId(long personId) {

    }

    @Override
    public void updateLivingPlaceInfo(LivingPlaceInfo entity) {

    }

    @Override
    public Person findLinkedPerson(long livingPlaceInfoId) {
        return null;
    }
}
