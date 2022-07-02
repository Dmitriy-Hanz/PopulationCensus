package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import populationcensus.repository.entity.Person;
import populationcensus.repository.entity.WorkInfo;
import populationcensus.repository.repositories.WorkInfoRep;
import populationcensus.service.interfaces.WorkInfoService;

@Service("workInfoService")
@AllArgsConstructor
public class WorkInfoServiceImpl implements WorkInfoService {

    private final WorkInfoRep workInfoRep;

    @Override
    public void saveWorkInfo(WorkInfo entity) {

    }

    @Override
    public void saveWorkInfo(long personId, WorkInfo entity) {

    }

    @Override
    public WorkInfo findWorkInfo(long workInfoId) {
        return null;
    }

    @Override
    public WorkInfo findWorkInfoByPerson(Person person) {
        return null;
    }

    @Override
    public WorkInfo findWorkInfoByPersonId(long personId) {
        return null;
    }

    @Override
    public void deleteWorkInfo(WorkInfo workInfo) {

    }

    @Override
    public void deleteWorkInfoById(long workInfoId) {

    }

    @Override
    public void deleteWorkInfoByPerson(Person person) {

    }

    @Override
    public void deleteWorkInfoByPersonId(long personId) {

    }

    @Override
    public void updateWorkInfo(WorkInfo entity) {

    }

    @Override
    public Person findLinkedPerson(int workInfoId) {
        return null;
    }

    @Override
    public Person findLinkedPerson(WorkInfo workInfo) {
        return null;
    }
}
