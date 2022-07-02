package populationcensus.service.interfaces;

import populationcensus.repository.entity.AccommodationsInfo;
import populationcensus.repository.entity.Household;

public interface AccommodationsInfoService {

    void saveAccommodationsInfo(AccommodationsInfo entity);
    void saveAccommodationsInfo(long householdId, AccommodationsInfo entity);
    void saveAccommodationsInfo(Household household, AccommodationsInfo entity);

    AccommodationsInfo findAccommodationsInfo(long accommodationsInfoId);
    AccommodationsInfo findAccommodationsInfoByHouseholdId(long householdId);
    AccommodationsInfo findAccommodationsInfoByHousehold(Household household);

    void deleteAccommodationsInfoById(long accommodationsInfoId);
    void deleteAccommodationsInfo(AccommodationsInfo accommodationsInfo);
    void deleteAccommodationsInfoByHouseholdId(long householdId);
    void deleteAccommodationsInfoByHousehold(Household household);

    Household findLinkedHousehold(long accommodationsInfoId);
    Household findLinkedHousehold(AccommodationsInfo accommodationsInfo);

    void updateAccommodationsInfo(AccommodationsInfo entity);
}
