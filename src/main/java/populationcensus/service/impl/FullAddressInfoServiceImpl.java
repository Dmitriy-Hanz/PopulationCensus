package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import populationcensus.repository.entity.FullAddressInfo;
import populationcensus.repository.entity.Household;
import populationcensus.repository.repositories.FullAddressInfoRep;
import populationcensus.repository.repositories.HouseholdRep;
import populationcensus.service.interfaces.FullAddressInfoService;

import java.util.Optional;

@Service("fullAddressInfoService")
@AllArgsConstructor
public class FullAddressInfoServiceImpl implements FullAddressInfoService {

    private final FullAddressInfoRep fullAddressInfoRep;
    private final HouseholdRep householdRep;

    @Override
    public void saveFullAddressInfo(FullAddressInfo entity) {
        if(entity.getHouseholdInFullAddressInfo() == null) {
            return;
        }
        fullAddressInfoRep.saveAndFlush(entity);
    }

    @Override
    public void saveFullAddressInfo(long householdId, FullAddressInfo entity) {
        if (entity.getHouseholdInFullAddressInfo() != null) {
            return;
        }
        Optional<Household> householdOptional = householdRep.findById(householdId);

        householdOptional.ifPresent(
                (obj) -> {
                    if(obj.getFullAddressInfo() != null){
                        return;
                    }
                    entity.setHouseholdInFullAddressInfo(householdOptional.get());
                    fullAddressInfoRep.saveAndFlush(entity);
                }
        );
    }

    @Override
    public void saveFullAddressInfo(Household household, FullAddressInfo entity) {
        if(entity.getHouseholdInFullAddressInfo() != null || household.getId() == null){
            return;
        }
        entity.setHouseholdInFullAddressInfo(household);
        fullAddressInfoRep.saveAndFlush(entity);
    }

    @Override
    public FullAddressInfo findFullAddressInfo(long fullAddressInfoId) {
        Optional<FullAddressInfo> result = fullAddressInfoRep.findById(fullAddressInfoId);
        return result.orElse(null);
    }
    @Override
    public FullAddressInfo findFullAddressInfoByHouseholdId(long householdId) {
        Optional<Household> householdOptional = householdRep.findById(householdId);
        Household householdResult = householdOptional.orElse(null);
        if (householdResult == null){
            return null;
        }
        return householdResult.getFullAddressInfo();
    }

    @Override
    public FullAddressInfo findFullAddressInfoByHousehold(Household household) {
        if (household == null) {
            return null;
        }
        return findFullAddressInfoByHouseholdId(household.getId());
    }


    @Override
    public void deleteFullAddressInfoById(long fullAddressInfo) {
        fullAddressInfoRep.deleteById(fullAddressInfo);
        fullAddressInfoRep.flush();
    }

    @Override
    public void deleteFullAddressInfo(FullAddressInfo fullAddressInfo) {
        if (fullAddressInfo == null){
            return;
        }
        deleteFullAddressInfoById(fullAddressInfo.getId());
    }

    @Override
    public void deleteFullAddressInfoByHouseholdId(long householdId) {
        Optional<Household> householdOptional = householdRep.findById(householdId);
        Household householdResult = householdOptional.orElse(null);
        if (householdResult == null){
            return;
        }
        deleteFullAddressInfo(householdResult.getFullAddressInfo());
        householdRep.flush();
    }

    @Override
    public void deleteFullAddressInfoByHousehold(Household household) {
        if(household == null){
            return;
        }
        deleteFullAddressInfoByHouseholdId(household.getId());
    }


    @Override
    public Household findLinkedHousehold(long fullAddressInfoId) {
        Optional<FullAddressInfo> fullAddressInfoOptional = fullAddressInfoRep.findById(fullAddressInfoId);
        FullAddressInfo fullAddressInfoResult = fullAddressInfoOptional.orElse(null);
        if (fullAddressInfoResult == null){
            return null;
        }
        return fullAddressInfoResult.getHouseholdInFullAddressInfo();
    }

    @Override
    public Household findLinkedHousehold(FullAddressInfo fullAddressInfo) {
        return findLinkedHousehold(fullAddressInfo.getId());
    }

}