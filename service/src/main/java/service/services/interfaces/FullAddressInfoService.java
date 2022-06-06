package service.services.interfaces;

import repository.entity.*;

public interface FullAddressInfoService {

    void createFullAddressInfo(int householdId);

    void deleteFullAddressInfo(int fullAddressInfoId);
    void deleteFullAddressInfoByHousehold(int householdId);

    void updateFullAddressInfo(FullAddressInfo entity);

    Household findLinkedHousehold(int fullAddressInfoId);
}
