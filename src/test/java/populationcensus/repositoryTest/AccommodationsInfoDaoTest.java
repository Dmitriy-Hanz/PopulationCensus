package populationcensus.repositoryTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import populationcensus.repository.entity.AccommodationsInfo;
import populationcensus.repository.entity.Household;
import populationcensus.repository.repositories.AccommodationsInfoRep;
import populationcensus.repository.repositories.HouseholdRep;

import java.util.Optional;

//@RunWith(SpringRunner.class)
//@DataJpaTest
public class AccommodationsInfoDaoTest {
//
//    @Autowired
//    private HouseholdRep householdRep;
//    @Autowired
//    private AccommodationsInfoRep accommodationsInfoRep;
//
//    private static Household household;
//    private static AccommodationsInfo accommodationsInfo;
//
//    @Test
//    public void t1_InsertTest()
//    {
//        accommodationsInfo = AccommodationsInfo.builder().areaOfFlat(228).build();
//        household = Household.builder().numberOfMembers(3).accommodationsInfo(accommodationsInfo).build();
//
//        householdRep.save(household);
//
//        Optional<Household> householdOptional = householdRep.findById(household.getId());
//
//        if(householdOptional.isEmpty()) {
//            Assertions.fail();
//        }
//        Household householdFromDB = householdOptional.get();
//        Assertions.assertNotNull(householdFromDB.getAccommodationsInfo());
//        Assertions.assertEquals(household.getId(),householdFromDB.getId());
//        Assertions.assertEquals(accommodationsInfo.getId(),householdFromDB.getAccommodationsInfo().getId());
//    }
//
//    @Test
//    public void t2_UpdateTest()
//    {
////        accommodationsInfo.setApartmentType(3);
////        accommodationsInfoRep.
//////        mupdate(accommodationsInfo);
////        Household householdFromDB = householdDao.selectById(household.getId());
////
////        assertNotNull(householdFromDB);
////        assertNotNull(householdFromDB.getAccommodationsInfo());
////        assertEquals(household.getId(),householdFromDB.getId());
////        assertEquals(accommodationsInfo.getApartmentType(),householdFromDB.getAccommodationsInfo().getApartmentType());
//    }
//
//    @Test
//    public void t3_DeleteTest()
//    {
////        householdDao.delete(household.getId());
////
////        Household householdFromDB = householdDao.selectById(household.getId());
////        AccommodationsInfo accommodationsInfoFromDB = accommodationsInfoDao.selectById(accommodationsInfo.getId());
////
////        assertNull(householdFromDB);
////        assertNull(accommodationsInfoFromDB);
//    }
}
