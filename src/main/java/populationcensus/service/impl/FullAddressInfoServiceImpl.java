package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import populationcensus.repository.entity.FullAddressInfo;
import populationcensus.repository.entity.Household;
import populationcensus.repository.repositories.FullAddressInfoRep;
import populationcensus.service.interfaces.FullAddressInfoService;

@Service("fullAddressInfoService")
@AllArgsConstructor
public class FullAddressInfoServiceImpl implements FullAddressInfoService {

    private final FullAddressInfoRep fullAddressInfoRep;

    @Override
    public void saveFullAddressInfo(FullAddressInfo entity) {

    }

    @Override
    public void saveFullAddressInfo(long householdId, FullAddressInfo entity) {

    }

    @Override
    public FullAddressInfo findFullAddressInfo(long householdId) {
        return null;
    }

    @Override
    public FullAddressInfo findFullAddressInfoByPerson(Household household) {
        return null;
    }

    @Override
    public FullAddressInfo findFullAddressInfoByPersonId(long personId) {
        return null;
    }

    @Override
    public void deleteFullAddressInfo(FullAddressInfo fullAddressInfo) {

    }

    @Override
    public void deleteFullAddressInfoById(long fullAddressInfo) {

    }

    @Override
    public void deleteFullAddressInfoByHousehold(Household household) {

    }

    @Override
    public void deleteFullAddressInfoByHouseholdId(long householdId) {

    }

    @Override
    public void updateFullAddressInfo(FullAddressInfo entity) {

    }

    @Override
    public Household findLinkedHousehold(long fullAddressInfoId) {
        return null;
    }
}