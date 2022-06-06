package repository.dao;

import repository.dao.daos.*;
import repository.dao.idaos.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoFactory {
    public static final String PERSISTENCE_UNIT_NAME = "unit";
    private final EntityManagerFactory factory;

    private static class SingletonHolder {
        private final static DaoFactory INSTANCE = new DaoFactory();
    }
    private DaoFactory() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    public static DaoFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }



    public HouseholdDaoImpl createHouseholdDao() {
        return new HouseholdDao(factory.createEntityManager());
    }

    public AccommodationsInfoDaoImpl createAccommodationsInfoDao() {
        return new AccommodationsInfoDao(factory.createEntityManager());
    }

    public FullAddressInfoDaoImpl createFullAddressInfoDao() {
        return new FullAddressInfoDao(factory.createEntityManager());
    }

    public PersonDaoImpl createPersonDao() {
        return new PersonDao(factory.createEntityManager());
    }

    public LivingPlaceInfoDaoImpl createLivingPlaceInfoDao() {
        return new LivingPlaceInfoDao(factory.createEntityManager());
    }

    public LivingCountryInfoDaoImpl createLivingCountryInfoDao() {
        return new LivingCountryInfoDao(factory.createEntityManager());
    }

    public EducationInfoDaoImpl createEducationInfoDao() {
        return new EducationInfoDao(factory.createEntityManager());
    }

    public WorkInfoDaoImpl createWorkInfoDao() {
        return new WorkInfoDao(factory.createEntityManager());
    }

    public ChildrenInfoDaoImpl createChildrenInfoDao() {
        return new ChildrenInfoDao(factory.createEntityManager());
    }
}
