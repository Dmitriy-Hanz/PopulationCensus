package populationcensus.service.interfaces;

import populationcensus.repository.entity.*;

public interface FullAddressInfoService {

    void saveFullAddressInfo(FullAddressInfo entity);
    void saveFullAddressInfo(long householdId, FullAddressInfo entity);

    FullAddressInfo findFullAddressInfo(long householdId);
    FullAddressInfo findFullAddressInfoByPerson(Household household);
    FullAddressInfo findFullAddressInfoByPersonId(long personId);

    void deleteFullAddressInfo(FullAddressInfo fullAddressInfo);
    void deleteFullAddressInfoById(long fullAddressInfo);
    void deleteFullAddressInfoByHousehold(Household household);
    void deleteFullAddressInfoByHouseholdId(long householdId);

    void updateFullAddressInfo(FullAddressInfo entity);

    Household findLinkedHousehold(long fullAddressInfoId);
}
