package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        if (entity.getPersonInLivingCountryInfo() == null) {
            return;
        }
        livingCountryInfoRep.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void saveLivingCountryInfo(long personId, LivingCountryInfo entity) {
        if (entity.getPersonInLivingCountryInfo() != null) {
            return;
        }
        Optional<Person> personOptional = personRep.findById(personId);

        personOptional.ifPresent(
                (obj) -> {
                    if (obj.getLivingCountryInfo() != null) {
                        return;
                    }
                    entity.setPersonInLivingCountryInfo(personOptional.get());
                    livingCountryInfoRep.saveAndFlush(entity);
                }
        );
    }

    @Override
    public void saveLivingCountryInfo(Person person, LivingCountryInfo entity) {
        if (entity.getPersonInLivingCountryInfo() != null || person.getId() == null) {
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
    @Transactional
    public LivingCountryInfo findLivingCountryInfoByPersonId(long personId) {
        Person person = personRep.findById(personId).orElse(null);
        return livingCountryInfoRep.findByPersonInLivingCountryInfo(person).orElse(null);
    }

    @Override
    public LivingCountryInfo findLivingCountryInfoByPerson(Person person) {
        return livingCountryInfoRep.findByPersonInLivingCountryInfo(person).orElse(null);
    }


    @Override
    public void deleteLivingCountryInfoById(long livingCountryInfoId) {
        livingCountryInfoRep.deleteById(livingCountryInfoId);
    }

    @Override
    public void deleteLivingCountryInfo(LivingCountryInfo livingCountryInfo) {
        livingCountryInfoRep.delete(livingCountryInfo);
    }

    @Override
    public void deleteLivingCountryInfoByPersonId(long personId) {
        Person person = personRep.findById(personId).orElse(null);
        livingCountryInfoRep.deleteByPersonInLivingCountryInfo(person);
    }

    @Override
    public void deleteLivingCountryInfoByPerson(Person person) {
        livingCountryInfoRep.deleteByPersonInLivingCountryInfo(person);
    }


    @Transactional
    @Override
    public Person findLinkedPerson(long livingCountryInfoId) {
        LivingCountryInfo livingCountryInfo = livingCountryInfoRep.findById(livingCountryInfoId).orElse(new LivingCountryInfo());
        return personRep.findByLivingCountryInfo(livingCountryInfo).orElse(null);
    }

    @Override
    public Person findLinkedPerson(LivingCountryInfo livingCountryInfo) {
        return personRep.findByLivingCountryInfo(livingCountryInfo).orElse(null);
    }
}
