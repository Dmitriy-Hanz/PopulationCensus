package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import populationcensus.repository.entity.ChildrenInfo;
import populationcensus.repository.entity.EducationInfo;
import populationcensus.repository.entity.Person;
import populationcensus.repository.repositories.EducationInfoRep;
import populationcensus.service.interfaces.EducationInfoService;

@Service("educationInfoService")
@AllArgsConstructor
public class EducationInfoServiceImpl implements EducationInfoService {

    private final EducationInfoRep educationInfoRep;

    @Override
    public void saveEducationInfo(EducationInfo entity) {

    }

    @Override
    public void saveEducationInfo(long personId, ChildrenInfo entity) {

    }

    @Override
    public EducationInfo findEducationInfo(long educationInfoId) {
        return null;
    }

    @Override
    public EducationInfo findEducationInfoByPerson(Person person) {
        return null;
    }

    @Override
    public EducationInfo findEducationInfoByPersonId(long personId) {
        return null;
    }

    @Override
    public void deleteEducationInfo(EducationInfo educationInfo) {

    }

    @Override
    public void deleteEducationInfoById(long educationInfoId) {

    }

    @Override
    public void deleteEducationInfoByPerson(Person person) {

    }

    @Override
    public void deleteEducationInfoByPersonId(long personId) {

    }

    @Override
    public void updateEducationInfo(EducationInfo entity) {

    }

    @Override
    public Person findLinkedPerson(long educationInfoId) {
        return null;
    }
}
