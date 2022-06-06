package repository;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import repository.dao.DaoFactory;
import repository.dao.idaos.ChildrenInfoDaoImpl;
import repository.dao.idaos.PersonDaoImpl;
import repository.entity.ChildrenInfo;
import repository.entity.Person;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChildrenInfoDaoTest{

    private PersonDaoImpl personDao;
    private ChildrenInfoDaoImpl childrenInfoDao;
    private static Person person;
    private static ChildrenInfo childrenInfo;

    @Before
    public void preset(){
        personDao = DaoFactory.getInstance().createPersonDao();
        childrenInfoDao = DaoFactory.getInstance().createChildrenInfoDao();
    }
    @After
    public void postset(){
        personDao.closeDao();
        childrenInfoDao.closeDao();
    }


    @Test
    public void t1_InsertTest()
    {
        childrenInfo = ChildrenInfo.builder().howManyChildrenDoYouHave(2).build();
        person = Person.builder().isForeign(false).childrenInfo(childrenInfo).build();

        personDao.insert(person);

        Person personFromDB = personDao.selectById(person.getId());

        assertNotNull(personFromDB);
        assertNotNull(personFromDB.getChildrenInfo());
        assertEquals(person.getId(),personFromDB.getId());
        assertEquals(childrenInfo.getId(),personFromDB.getChildrenInfo().getId());
    }

    @Test
    public void t2_UpdateTest()
    {
        childrenInfo.setHowManyChildrenDoYouHave(1);
        childrenInfoDao.update(childrenInfo);

        Person personFromDB = personDao.selectById(person.getId());

        assertNotNull(personFromDB);
        assertNotNull(personFromDB.getChildrenInfo());
        assertEquals(person.getId(),personFromDB.getId());
        assertEquals(childrenInfo.getHowManyChildrenDoYouHave(),personFromDB.getChildrenInfo().getHowManyChildrenDoYouHave());
    }

    @Test
    public void t3_DeleteTest()
    {
        personDao.delete(person.getId());

        Person personFromDB = personDao.selectById(person.getId());
        ChildrenInfo childrenInfoFromDB = childrenInfoDao.selectById(childrenInfo.getId());

        assertNull(personFromDB);
        assertNull(childrenInfoFromDB);
    }
}
