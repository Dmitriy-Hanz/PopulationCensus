package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import populationcensus.repository.entity.AccommodationsInfo;
import populationcensus.repository.entity.FullAddressInfo;
import populationcensus.repository.entity.Household;
import populationcensus.repository.entity.Person;
import populationcensus.repository.repositories.AccommodationsInfoRep;
import populationcensus.repository.repositories.HouseholdRep;
import populationcensus.service.interfaces.AccommodationsInfoService;
import populationcensus.service.interfaces.HouseholdService;

import java.util.List;
import java.util.Optional;

@Service("householdService")
@AllArgsConstructor
public class HouseholdServiceImpl implements HouseholdService {

    private final HouseholdRep householdRep;
    private final AccommodationsInfoRep accommodationsInfoRep;

    @Override
    public void saveHousehold(Household entity) {
        if (entity.getAccommodationsInfo() != null){
            if(entity.getAccommodationsInfo().getHouseholdInAccommodationsInfo() == null){
                entity.getAccommodationsInfo().setHouseholdInAccommodationsInfo(entity);
            }
        }
        if (entity.getFullAddressInfo() != null){
            if(entity.getFullAddressInfo().getHouseholdInFullAddressInfo() == null){
                entity.getFullAddressInfo().setHouseholdInFullAddressInfo(entity);
            }
        }
        householdRep.saveAndFlush(entity);
    }

    @Override
    public void updateHousehold(Household entity) {
        householdRep.flush();
    }

    @Override
    public Household findHousehold(long householdId) {
        Optional<Household> result = householdRep.findById(householdId);
        if(result.isEmpty()){
            return null;
        }
        return result.get();
    }

    @Override
    public Household findHouseholdByPerson(Person person) {
        return null;
    }

    @Override
    public Household findHouseholdByPersonId(long personId) {
        return null;
    }

    @Override
    public Household findHouseholdByAccommodationsInfo(AccommodationsInfo accommodationsInfo) {
        return null;
    }

    @Override
    public Household findHouseholdByAccommodationsInfoId(long accommodationsInfoId) {
        return null;
    }

    @Override
    public Household findHouseholdByFullAddressInfo(FullAddressInfo fullAddressInfo) {
        return null;
    }

    @Override
    public Household findHouseholdByFullAddressInfoId(long fullAddressInfoId) {
        return null;
    }

    @Override
    public void deleteHousehold(Household household) {

    }

    @Override
    public void deleteHouseholdById(long householdId) {
        householdRep.deleteById(householdId);
        householdRep.flush();
    }

    @Override
    public void deleteHouseholdByPerson(Person person) {

    }

    @Override
    public void deleteHouseholdByPersonId(long personId) {

    }

    @Override
    public void deleteHouseholdByAccommodationsInfo(AccommodationsInfo accommodationsInfo) {

    }

    @Override
    public void deleteHouseholdByFullAddressInfo(FullAddressInfo fullAddressInfo) {

    }

    @Override
    public void deleteHouseholdByAccommodationsInfoId(long accommodationsInfoId) {

    }

    @Override
    public void deleteHouseholdByFullAddressInfoId(long fullAddressInfoId) {

    }

    @Override
    public List<Person> findLinkedPersons(long householdId) {
        return null;
    }

    @Override
    public List<Person> findLinkedPersons(Household household) {
        return null;
    }

}
