package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import populationcensus.repository.entity.LivingCountryInfo;
import populationcensus.repository.entity.Person;
import populationcensus.repository.repositories.LivingCountryInfoRep;
import populationcensus.repository.repositories.PersonRep;
import populationcensus.service.interfaces.LivingCountryInfoService;

import java.util.Optional;

@Service("livingCountryInfoService")
@AllArgsConstructor
public class LivingCountryInfoServiceImpl implements LivingCountryInfoService {

    private final LivingCountryInfoRep livingCountryInfoRep;
    private final PersonRep personRep;

    @Override
    public void saveLivingCountryInfo(LivingCountryInfo entity) {
        if(entity.getPersonInLivingCountryInfo() == null) {
            return;
        }
        livingCountryInfoRep.saveAndFlush(entity);
    }

    @Override
    public void saveLivingCountryInfo(long personId, LivingCountryInfo entity) {
        if (entity.getPersonInLivingCountryInfo() != null) {
            return;
        }
        Optional<Person> personOptional = personRep.findById(personId);

        personOptional.ifPresent(
                (obj) -> {
                    if(obj.getLivingCountryInfo() != null){
                        return;
                    }
                    entity.setPersonInLivingCountryInfo(personOptional.get());
                    livingCountryInfoRep.saveAndFlush(entity);
                }
        );
    }

    @Override
    public void saveLivingCountryInfo(Person person, LivingCountryInfo entity) {
        if(entity.getPersonInLivingCountryInfo() != null || person.getId() == null){
            return;
        }
        entity.setPersonInLivingCountryInfo(person);
        livingCountryInfoRep.saveAndFlush(entity);
    }


    @Override
    public LivingCountryInfo findLivingCountryInfo(long livingCountryInfoId) {
        Optional<LivingCountryInfo> result = livingCountryInfoRep.findById(livingCountryInfoId);
        return result.orElse(null);
    }

    @Override
    public LivingCountryInfo findLivingCountryInfoByPersonId(long personId) {
        Optional<Person> personOptional = personRep.findById(personId);
        Person personResult = personOptional.orElse(null);
        if (personResult == null){
            return null;
        }
        return personResult.getLivingCountryInfo();
    }

    @Override
    public LivingCountryInfo findLivingCountryInfoByPerson(Person person) {
        if (person == null) {
            return null;
        }
        return findLivingCountryInfoByPersonId(person.getId());
    }


    @Override
    public void deleteLivingCountryInfoById(long livingCountryInfoId) {
        livingCountryInfoRep.deleteById(livingCountryInfoId);
        livingCountryInfoRep.flush();
    }

    @Override
    public void deleteLivingCountryInfo(LivingCountryInfo livingCountryInfo) {
        if (livingCountryInfo == null){
            return;
        }
        deleteLivingCountryInfoById(livingCountryInfo.getId());
    }

    @Override
    public void deleteLivingCountryInfoByPersonId(long personId) {
        Optional<Person> personOptional = personRep.findById(personId);
        Person personResult = personOptional.orElse(null);
        if (personResult == null){
            return;
        }
        deleteLivingCountryInfo(personResult.getLivingCountryInfo());
        personRep.flush();
    }

    @Override
    public void deleteLivingCountryInfoByPerson(Person person) {
        if(person == null){
            return;
        }
        deleteLivingCountryInfoByPersonId(person.getId());
    }



    @Override
    public Person findLinkedPerson(long livingCountryInfoId) {
        Optional<LivingCountryInfo> livingCountryInfoOptional = livingCountryInfoRep.findById(livingCountryInfoId);
        LivingCountryInfo livingCountryInfoResult = livingCountryInfoOptional.orElse(null);
        if (livingCountryInfoResult == null){
            return null;
        }
        return livingCountryInfoResult.getPersonInLivingCountryInfo();
    }

    @Override
    public Person findLinkedPerson(LivingCountryInfo livingCountryInfo) {
        return findLinkedPerson(livingCountryInfo.getId());
    }


    @Override
    public void updateLivingCountryInfo(LivingCountryInfo entity) {
        if (entity == null){
            return;
        }
        livingCountryInfoRep.saveAndFlush(entity);
    }
}
