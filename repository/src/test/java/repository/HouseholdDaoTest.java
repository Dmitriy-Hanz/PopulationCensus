package repository;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import repository.dao.DaoFactory;
import repository.dao.idaos.HouseholdDaoImpl;
import repository.entity.Household;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HouseholdDaoTest {
    private HouseholdDaoImpl householdDao;
    private static Household household;
    private static Household household2;

    @Before
    public void preset(){
        householdDao = DaoFactory.getInstance().createHouseholdDao();
    }
    @After
    public void postset(){
        householdDao.closeDao();
    }

    @Test
    public void t1_InsertTest()
    {
        household = Household.builder().numberOfMembers(3).build();
        householdDao.insert(household);

        Household householdFromDB = householdDao.selectById(household.getId());
        assertNotNull(householdFromDB);
        assertEquals(household.getId(),householdFromDB.getId());
    }

    @Test
    public void t2_UpdateTest()
    {
        household.setRoomsCount(2);
        householdDao.update(household);

        Household householdFromDB = householdDao.selectById(household.getId());
        assertNotNull(householdFromDB);
        assertEquals(household.getId(),householdFromDB.getId());
        assertEquals(household.getRoomsCount(),householdFromDB.getRoomsCount());
    }

    @Test
    public void t3_DeleteTest()
    {
        householdDao.delete(household.getId());

        Household householdFromDB = householdDao.selectById(household.getId());
        assertNull(householdFromDB);
    }

    @Test
    public void t4_InsertGroupTest()
    {
        List<Household> households = new ArrayList<>();
        household = Household.builder().numberOfMembers(2).build();
        household2 = Household.builder().numberOfMembers(4).build();
        households.add(household);
        households.add(household2);

        householdDao.insert(households);

        Household household1FromDB = householdDao.selectById(household.getId());
        Household household2FromDB = householdDao.selectById(household2.getId());

        assertNotNull(household1FromDB);
        assertNotNull(household2FromDB);
        assertNotEquals(household1FromDB,household2FromDB);
        assertEquals(household.getId(),household1FromDB.getId());
        assertEquals(household2.getId(),household2FromDB.getId());
    }

    @Test
    public void t5_SelectAllTest()
    {
        List<Household> householdsFromDB = householdDao.selectAll();
        assertNotNull(householdsFromDB);
        assertEquals(2,householdsFromDB.size());
        assertEquals(household.getId(),householdsFromDB.get(0).getId());
        assertEquals(household2.getId(),householdsFromDB.get(1).getId());
    }
}
