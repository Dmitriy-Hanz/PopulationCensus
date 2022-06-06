package repository.dao.daos;

import repository.dao.daos.base.Dao;
import repository.dao.idaos.WorkInfoDaoImpl;
import repository.entity.WorkInfo;

import javax.persistence.EntityManager;

public class WorkInfoDao extends Dao<WorkInfo> implements WorkInfoDaoImpl {

    public WorkInfoDao(EntityManager em) {
        super(em, WorkInfo.class);
    }
}
