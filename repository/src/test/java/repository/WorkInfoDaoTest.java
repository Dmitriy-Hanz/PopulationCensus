package repository;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import repository.dao.DaoFactory;
import repository.dao.idaos.PersonDaoImpl;
import repository.dao.idaos.WorkInfoDaoImpl;
import repository.entity.Person;
import repository.entity.WorkInfo;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WorkInfoDaoTest {

    private PersonDaoImpl personDao;
    private WorkInfoDaoImpl workInfoDao;
    private static Person person;
    private static WorkInfo workInfo;

    @Before
    public void preset(){
        personDao = DaoFactory.getInstance().createPersonDao();
        workInfoDao = DaoFactory.getInstance().createWorkInfoDao();
    }
    @After
    public void postset(){
        personDao.closeDao();
        workInfoDao.closeDao();
    }


    @Test
    public void t1_InsertTest()
    {
        workInfo = WorkInfo.builder().typeOfWorkplace(2).build();
        person = Person.builder().isForeign(false).workInfo(workInfo).build();

        personDao.insert(person);

        Person personFromDB = personDao.selectById(person.getId());

        assertNotNull(personFromDB);
        assertNotNull(personFromDB.getWorkInfo());
        assertEquals(person.getId(),personFromDB.getId());
        assertEquals(workInfo.getId(),personFromDB.getWorkInfo().getId());
    }

    @Test
    public void t2_UpdateTest()
    {
        workInfo.setTypeOfWorkplace(2);
        workInfoDao.update(workInfo);

        Person personFromDB = personDao.selectById(person.getId());

        assertNotNull(personFromDB);
        assertNotNull(personFromDB.getWorkInfo());
        assertEquals(person.getId(),personFromDB.getId());
        assertEquals(workInfo.getTypeOfWorkplace(),personFromDB.getWorkInfo().getTypeOfWorkplace());
    }

    @Test
    public void t3_DeleteTest()
    {
        personDao.delete(person.getId());

        Person personFromDB = personDao.selectById(person.getId());
        WorkInfo workInfoFromDB = workInfoDao.selectById(workInfo.getId());

        assertNull(personFromDB);
        assertNull(workInfoFromDB);
    }
}
