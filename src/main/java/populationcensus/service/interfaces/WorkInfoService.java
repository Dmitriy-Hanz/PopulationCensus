package populationcensus.service.interfaces;

import populationcensus.repository.entity.*;

public interface WorkInfoService {

    void saveWorkInfo(WorkInfo entity);
    void saveWorkInfo(long personId, WorkInfo entity);

    WorkInfo findWorkInfo(long workInfoId);
    WorkInfo findWorkInfoByPerson(Person person);
    WorkInfo findWorkInfoByPersonId(long personId);

    void deleteWorkInfo(WorkInfo workInfo);
    void deleteWorkInfoById(long workInfoId);
    void deleteWorkInfoByPerson(Person person);
    void deleteWorkInfoByPersonId(long personId);

    void updateWorkInfo(WorkInfo entity);

    Person findLinkedPerson(int workInfoId);
    Person findLinkedPerson(WorkInfo workInfo);
}
