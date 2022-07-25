package populationcensus.service.interfaces;

import populationcensus.repository.entity.Household;

public interface HouseholdService {

    void saveHousehold(Household entity);

    Household findHousehold(long householdId);

    void deleteHouseholdById(long householdId);
}
