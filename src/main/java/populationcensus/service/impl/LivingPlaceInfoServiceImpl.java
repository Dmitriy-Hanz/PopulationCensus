package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import populationcensus.repository.entity.LivingPlaceInfo;
import populationcensus.repository.entity.LivingPlaceInfo;
import populationcensus.repository.entity.Person;
import populationcensus.repository.repositories.LivingPlaceInfoRep;
import populationcensus.repository.repositories.PersonRep;
import populationcensus.service.interfaces.LivingPlaceInfoService;

import java.util.Optional;

@Service("livingPlaceInfoService")
@AllArgsConstructor
public class LivingPlaceInfoServiceImpl implements LivingPlaceInfoService {

    private final LivingPlaceInfoRep livingPlaceInfoRep;
    private final PersonRep personRep;

    @Override
    public void saveLivingPlaceInfo(LivingPlaceInfo entity) {
        if(entity.getPersonInLivingPlaceInfo() == null) {
            return;
        }
        livingPlaceInfoRep.saveAndFlush(entity);
    }

    @Override
    public void saveLivingPlaceInfo(long personId, LivingPlaceInfo entity) {
        if (entity.getPersonInLivingPlaceInfo() != null) {
            return;
        }
        Optional<Person> personOptional = personRep.findById(personId);

        personOptional.ifPresent(
                (obj) -> {
                    if(obj.getLivingPlaceInfo() != null){
                        return;
                    }
                    entity.setPersonInLivingPlaceInfo(personOptional.get());
                    livingPlaceInfoRep.saveAndFlush(entity);
                }
        );
    }

    @Override
    public void saveLivingPlaceInfo(Person person, LivingPlaceInfo entity) {
        if(entity.getPersonInLivingPlaceInfo() != null || person.getId() == null){
            return;
        }
        entity.setPersonInLivingPlaceInfo(person);
        livingPlaceInfoRep.saveAndFlush(entity);
    }


    @Override
    public LivingPlaceInfo findLivingPlaceInfo(long livingPlaceInfoId) {
        Optional<LivingPlaceInfo> result = livingPlaceInfoRep.findById(livingPlaceInfoId);
        return result.orElse(null);
    }

    @Override
    public LivingPlaceInfo findLivingPlaceInfoByPersonId(long personId) {
        Optional<Person> personOptional = personRep.findById(personId);
        Person personResult = personOptional.orElse(null);
        if (personResult == null){
            return null;
        }
        return personResult.getLivingPlaceInfo();
    }

    @Override
    public LivingPlaceInfo findLivingPlaceInfoByPerson(Person person) {
        if (person == null) {
            return null;
        }
        return findLivingPlaceInfoByPersonId(person.getId());
    }


    @Override
    public void deleteLivingPlaceInfoById(long livingPlaceInfoId) {
        livingPlaceInfoRep.deleteById(livingPlaceInfoId);
        livingPlaceInfoRep.flush();
    }

    @Override
    public void deleteLivingPlaceInfo(LivingPlaceInfo livingPlaceInfo) {
        if (livingPlaceInfo == null){
            return;
        }
        deleteLivingPlaceInfoById(livingPlaceInfo.getId());
    }

    @Override
    public void deleteLivingPlaceInfoByPersonId(long personId) {
        Optional<Person> personOptional = personRep.findById(personId);
        Person personResult = personOptional.orElse(null);
        if (personResult == null){
            return;
        }
        deleteLivingPlaceInfo(personResult.getLivingPlaceInfo());
        personRep.flush();
    }

    @Override
    public void deleteLivingPlaceInfoByPerson(Person person) {
        if(person == null){
            return;
        }
        deleteLivingPlaceInfoByPersonId(person.getId());
    }



    @Override
    public Person findLinkedPerson(long livingPlaceInfoId) {
        Optional<LivingPlaceInfo> livingPlaceInfoOptional = livingPlaceInfoRep.findById(livingPlaceInfoId);
        LivingPlaceInfo livingPlaceInfoResult = livingPlaceInfoOptional.orElse(null);
        if (livingPlaceInfoResult == null){
            return null;
        }
        return livingPlaceInfoResult.getPersonInLivingPlaceInfo();
    }

    @Override
    public Person findLinkedPerson(LivingPlaceInfo livingPlaceInfo) {
        return findLinkedPerson(livingPlaceInfo.getId());
    }


    @Override
    public void updateLivingPlaceInfo(LivingPlaceInfo entity) {
        if (entity == null){
            return;
        }
        livingPlaceInfoRep.saveAndFlush(entity);
    }
}
