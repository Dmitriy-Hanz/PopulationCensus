package populationcensus.service.interfaces;

import populationcensus.repository.entity.*;

public interface FullAddressInfoService {

    void saveFullAddressInfo(FullAddressInfo entity);
    void saveFullAddressInfo(long householdId, FullAddressInfo entity);
    void saveFullAddressInfo(Household household, FullAddressInfo entity);

    FullAddressInfo findFullAddressInfo(long householdId);
    FullAddressInfo findFullAddressInfoByHouseholdId(long household);
    FullAddressInfo findFullAddressInfoByHousehold(Household household);

    void deleteFullAddressInfoById(long fullAddressInfo);
    void deleteFullAddressInfo(FullAddressInfo fullAddressInfo);
    void deleteFullAddressInfoByHouseholdId(long householdId);
    void deleteFullAddressInfoByHousehold(Household household);

    Household findLinkedHousehold(long fullAddressInfoId);
    Household findLinkedHousehold(FullAddressInfo fullAddressInfo);

    void updateFullAddressInfo(FullAddressInfo entity);
}
