package repository;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import repository.dao.DaoFactory;
import repository.dao.idaos.AccommodationsInfoDaoImpl;
import repository.dao.idaos.HouseholdDaoImpl;
import repository.entity.AccommodationsInfo;
import repository.entity.Household;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccommodationsInfoDaoTest {
    private HouseholdDaoImpl householdDao;
    private AccommodationsInfoDaoImpl accommodationsInfoDao;
    private static Household household;
    private static AccommodationsInfo accommodationsInfo;

    @Before
    public void preset(){
        householdDao = DaoFactory.getInstance().createHouseholdDao();
        accommodationsInfoDao = DaoFactory.getInstance().createAccommodationsInfoDao();
    }
    @After
    public void postset(){
        householdDao.closeDao();
        accommodationsInfoDao.closeDao();
    }

    @Test
    public void t1_InsertTest()
    {
        accommodationsInfo = AccommodationsInfo.builder().areaOfFlat(228).build();
        household = Household.builder().numberOfMembers(3).accommodationsInfo(accommodationsInfo).build();

        householdDao.insert(household);

        Household householdFromDB = householdDao.selectById(household.getId());

        assertNotNull(householdFromDB);
        assertNotNull(householdFromDB.getAccommodationsInfo());
        assertEquals(household.getId(),householdFromDB.getId());
        assertEquals(accommodationsInfo.getId(),householdFromDB.getAccommodationsInfo().getId());
    }

    @Test
    public void t2_UpdateTest()
    {
        accommodationsInfo.setApartmentType(3);
        accommodationsInfoDao.update(accommodationsInfo);

        Household householdFromDB = householdDao.selectById(household.getId());

        assertNotNull(householdFromDB);
        assertNotNull(householdFromDB.getAccommodationsInfo());
        assertEquals(household.getId(),householdFromDB.getId());
        assertEquals(accommodationsInfo.getApartmentType(),householdFromDB.getAccommodationsInfo().getApartmentType());
    }

    @Test
    public void t3_DeleteTest()
    {
        householdDao.delete(household.getId());

        Household householdFromDB = householdDao.selectById(household.getId());
        AccommodationsInfo accommodationsInfoFromDB = accommodationsInfoDao.selectById(accommodationsInfo.getId());

        assertNull(householdFromDB);
        assertNull(accommodationsInfoFromDB);
    }
}
