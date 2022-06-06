package repository.dao.daos;

import repository.dao.daos.base.Dao;
import repository.dao.idaos.EducationInfoDaoImpl;
import repository.entity.EducationInfo;

import javax.persistence.EntityManager;

public class EducationInfoDao extends Dao<EducationInfo> implements EducationInfoDaoImpl {

    public EducationInfoDao(EntityManager em) {
        super(em, EducationInfo.class);
    }
}
