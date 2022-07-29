package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import populationcensus.repository.entity.AccommodationsInfo;
import populationcensus.repository.entity.Household;
import populationcensus.repository.repositories.AccommodationsInfoRep;
import populationcensus.repository.repositories.HouseholdRep;
import populationcensus.service.interfaces.AccommodationsInfoService;

import java.util.Optional;

@Service("accommodationsInfoService")
@AllArgsConstructor
public class AccommodationsInfoServiceImpl implements AccommodationsInfoService {

    private final AccommodationsInfoRep accommodationsInfoRep;
    private final HouseholdRep householdRep;

    @Override
    public void saveAccommodationsInfo(AccommodationsInfo entity) {
        if(entity.getHouseholdInAccommodationsInfo() == null) {
            return;
        }
        accommodationsInfoRep.saveAndFlush(entity);
    }

    @Override
    public void saveAccommodationsInfo(long householdId, AccommodationsInfo entity) {
        if (entity.getHouseholdInAccommodationsInfo() != null) {
            return;
        }
        Optional<Household> householdOptional = householdRep.findById(householdId);

        householdOptional.ifPresent(
                (obj) -> {
                    if(obj.getAccommodationsInfo() != null){
                        return;
                    }
                    entity.setHouseholdInAccommodationsInfo(householdOptional.get());
                    accommodationsInfoRep.saveAndFlush(entity);
                }
        );
    }

    @Override
    public void saveAccommodationsInfo(Household household, AccommodationsInfo entity) {
        if(entity.getHouseholdInAccommodationsInfo() != null || household.getId() == null){
            return;
        }
        entity.setHouseholdInAccommodationsInfo(household);
        accommodationsInfoRep.saveAndFlush(entity);
    }


    @Override
    public AccommodationsInfo findAccommodationsInfo(long accommodationsInfoId) {
        Optional<AccommodationsInfo> result = accommodationsInfoRep.findById(accommodationsInfoId);
        return result.orElse(null);
    }

    @Override
    public AccommodationsInfo findAccommodationsInfoByHouseholdId(long householdId) {
        Optional<Household> householdOptional = householdRep.findById(householdId);
        Household householdResult = householdOptional.orElse(null);
        if (householdResult == null){
            return null;
        }
        return householdResult.getAccommodationsInfo();
    }

    @Override
    public AccommodationsInfo findAccommodationsInfoByHousehold(Household household) {
        if (household == null) {
            return null;
        }
        return findAccommodationsInfoByHouseholdId(household.getId());
    }


    @Override
    public void deleteAccommodationsInfoById(long accommodationsInfoId) {
        accommodationsInfoRep.deleteById(accommodationsInfoId);
        accommodationsInfoRep.flush();
    }

    @Override
    public void deleteAccommodationsInfo(AccommodationsInfo accommodationsInfo) {
        if (accommodationsInfo == null){
            return;
        }
        deleteAccommodationsInfoById(accommodationsInfo.getId());
    }

    @Override
    public void deleteAccommodationsInfoByHouseholdId(long householdId) {
        Optional<Household> householdOptional = householdRep.findById(householdId);
        Household householdResult = householdOptional.orElse(null);
        if (householdResult == null){
            return;
        }
        deleteAccommodationsInfo(householdResult.getAccommodationsInfo());
        householdRep.flush();
    }

    @Override
    public void deleteAccommodationsInfoByHousehold(Household household) {
        if(household == null){
            return;
        }
        deleteAccommodationsInfoByHouseholdId(household.getId());
    }


    @Override
    public Household findLinkedHousehold(long accommodationsInfoId) {
        Optional<AccommodationsInfo> accommodationsInfoOptional = accommodationsInfoRep.findById(accommodationsInfoId);
        AccommodationsInfo accommodationsInfoResult = accommodationsInfoOptional.orElse(null);
        if (accommodationsInfoResult == null){
            return null;
        }
        return accommodationsInfoResult.getHouseholdInAccommodationsInfo();
    }

    @Override
    public Household findLinkedHousehold(AccommodationsInfo accommodationsInfo) {
        return findLinkedHousehold(accommodationsInfo.getId());
    }

}
