package repository.dao.daos;

import repository.dao.daos.base.Dao;
import repository.dao.idaos.LivingPlaceInfoDaoImpl;
import repository.entity.LivingPlaceInfo;

import javax.persistence.EntityManager;

public class LivingPlaceInfoDao extends Dao<LivingPlaceInfo> implements LivingPlaceInfoDaoImpl {

    public LivingPlaceInfoDao(EntityManager em) {
        super(em, LivingPlaceInfo.class);
    }
}
