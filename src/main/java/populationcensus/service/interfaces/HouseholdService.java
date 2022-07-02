package populationcensus.service.interfaces;

import populationcensus.repository.entity.AccommodationsInfo;
import populationcensus.repository.entity.FullAddressInfo;
import populationcensus.repository.entity.Household;
import populationcensus.repository.entity.Person;

import java.util.List;

public interface HouseholdService {

    void saveHousehold(Household entity);
    void updateHousehold(Household entity);

    Household findHousehold(long householdId);
    Household findHouseholdByPerson(Person person);
    Household findHouseholdByPersonId(long personId);
    Household findHouseholdByAccommodationsInfo(AccommodationsInfo accommodationsInfo);
    Household findHouseholdByAccommodationsInfoId(long accommodationsInfoId);
    Household findHouseholdByFullAddressInfo(FullAddressInfo fullAddressInfo);
    Household findHouseholdByFullAddressInfoId(long fullAddressInfoId);

    void deleteHousehold(Household household);
    void deleteHouseholdById(long householdId);
    void deleteHouseholdByPerson(Person person);
    void deleteHouseholdByPersonId(long personId);
    void deleteHouseholdByAccommodationsInfo(AccommodationsInfo accommodationsInfo);
    void deleteHouseholdByFullAddressInfo(FullAddressInfo fullAddressInfo);
    void deleteHouseholdByAccommodationsInfoId(long accommodationsInfoId);
    void deleteHouseholdByFullAddressInfoId(long fullAddressInfoId);

    List<Person> findLinkedPersons(long householdId);
    List<Person> findLinkedPersons(Household household);
}
