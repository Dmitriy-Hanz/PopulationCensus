package service.services.interfaces;

import repository.entity.AccommodationsInfo;
import repository.entity.Household;

public interface AccommodationsInfoService {

    void createAccommodationsInfo(int householdId);

    void deleteAccommodationsInfo(int accommodationsInfoId);
    void deleteAccommodationsInfoByHousehold(int householdId);

    void updateAccommodationsInfo(AccommodationsInfo entity);

    Household findLinkedHousehold(int accommodationsInfoId);
}
