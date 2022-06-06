package repository.dao.daos;


import repository.dao.daos.base.Dao;
import repository.dao.idaos.AccommodationsInfoDaoImpl;
import repository.entity.AccommodationsInfo;

import javax.persistence.EntityManager;

public class AccommodationsInfoDao extends Dao<AccommodationsInfo> implements AccommodationsInfoDaoImpl {

    public AccommodationsInfoDao(EntityManager em) {
        super(em, AccommodationsInfo.class);
    }
}
