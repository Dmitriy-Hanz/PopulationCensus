package populationcensus.service.interfaces;

import populationcensus.repository.entity.*;

public interface WorkInfoService {

    void saveWorkInfo(WorkInfo entity);
    void saveWorkInfo(long personId, WorkInfo entity);
    void saveWorkInfo(Person person, WorkInfo entity);

    WorkInfo findWorkInfo(long workInfoId);
    WorkInfo findWorkInfoByPersonId(long personId);
    WorkInfo findWorkInfoByPerson(Person person);

    void deleteWorkInfoById(long workInfoId);
    void deleteWorkInfo(WorkInfo workInfo);
    void deleteWorkInfoByPersonId(long personId);
    void deleteWorkInfoByPerson(Person person);

    Person findLinkedPerson(long workInfoId);
    Person findLinkedPerson(WorkInfo workInfo);

    void updateWorkInfo(WorkInfo entity);
}
