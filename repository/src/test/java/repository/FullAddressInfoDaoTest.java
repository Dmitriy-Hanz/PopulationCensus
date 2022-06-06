package repository;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import repository.dao.DaoFactory;
import repository.dao.idaos.FullAddressInfoDaoImpl;
import repository.dao.idaos.HouseholdDaoImpl;
import repository.entity.FullAddressInfo;
import repository.entity.Household;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FullAddressInfoDaoTest {
    private HouseholdDaoImpl householdDao;
    private FullAddressInfoDaoImpl fullAddressInfoDao;
    private static Household household;
    private static FullAddressInfo fullAddressInfo;

    @Test
    public void t1_InsertTest()
    {
        householdDao = DaoFactory.getInstance().createHouseholdDao();
        fullAddressInfoDao = DaoFactory.getInstance().createFullAddressInfoDao();

        fullAddressInfo = FullAddressInfo.builder().city("Купянск").build();
        household = Household.builder().numberOfMembers(3).fullAddressInfo(fullAddressInfo).build();

        householdDao.insert(household);

        Household householdFromDB = householdDao.selectById(household.getId());

        assertNotNull(householdFromDB);
        assertNotNull(householdFromDB.getFullAddressInfo());
        assertEquals(household.getId(),householdFromDB.getId());
        assertEquals(fullAddressInfo.getId(),householdFromDB.getFullAddressInfo().getId());

        householdDao.closeDao();
        fullAddressInfoDao.closeDao();
    }

    @Test
    public void t2_UpdateTest()
    {
        householdDao = DaoFactory.getInstance().createHouseholdDao();
        fullAddressInfoDao = DaoFactory.getInstance().createFullAddressInfoDao();

        fullAddressInfo.setCityDistrict("ЫЫЫ");
        fullAddressInfoDao.update(fullAddressInfo);

        Household householdFromDB = householdDao.selectById(household.getId());

        assertNotNull(householdFromDB);
        assertNotNull(householdFromDB.getFullAddressInfo());
        assertEquals(household.getId(),householdFromDB.getId());
        assertEquals(fullAddressInfo.getCityDistrict(),householdFromDB.getFullAddressInfo().getCityDistrict());

        householdDao.closeDao();
        fullAddressInfoDao.closeDao();
    }

    @Test
    public void t3_DeleteTest()
    {
        householdDao = DaoFactory.getInstance().createHouseholdDao();
        fullAddressInfoDao = DaoFactory.getInstance().createFullAddressInfoDao();

        householdDao.delete(household.getId());

        Household householdFromDB = householdDao.selectById(household.getId());
        FullAddressInfo fullAddressInfoFromDB = fullAddressInfoDao.selectById(fullAddressInfo.getId());

        assertNull(householdFromDB);
        assertNull(fullAddressInfoFromDB);

        fullAddressInfoDao.closeDao();
        householdDao.closeDao();
    }
}
