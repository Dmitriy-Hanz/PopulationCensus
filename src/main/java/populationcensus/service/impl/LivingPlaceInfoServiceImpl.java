package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        if (entity.getPersonInLivingPlaceInfo() == null) {
            return;
        }
        livingPlaceInfoRep.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void saveLivingPlaceInfo(long personId, LivingPlaceInfo entity) {
        if (entity.getPersonInLivingPlaceInfo() != null) {
            return;
        }
        Optional<Person> personOptional = personRep.findById(personId);

        personOptional.ifPresent(
                (obj) -> {
                    if (obj.getLivingPlaceInfo() != null) {
                        return;
                    }
                    entity.setPersonInLivingPlaceInfo(personOptional.get());
                    livingPlaceInfoRep.saveAndFlush(entity);
                }
        );
    }

    @Override
    public void saveLivingPlaceInfo(Person person, LivingPlaceInfo entity) {
        if (entity.getPersonInLivingPlaceInfo() != null || person.getId() == null) {
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
    @Transactional
    public LivingPlaceInfo findLivingPlaceInfoByPersonId(long personId) {
        Person person = personRep.findById(personId).orElse(null);
        return livingPlaceInfoRep.findByPersonInLivingPlaceInfo(person).orElse(null);
    }

    @Override
    public LivingPlaceInfo findLivingPlaceInfoByPerson(Person person) {
        return livingPlaceInfoRep.findByPersonInLivingPlaceInfo(person).orElse(null);
    }


    @Override
    public void deleteLivingPlaceInfoById(long livingPlaceInfoId) {
        livingPlaceInfoRep.deleteById(livingPlaceInfoId);
    }

    @Override
    public void deleteLivingPlaceInfo(LivingPlaceInfo livingPlaceInfo) {
        livingPlaceInfoRep.delete(livingPlaceInfo);
    }

    @Override
    public void deleteLivingPlaceInfoByPersonId(long personId) {
        Person person = personRep.findById(personId).orElse(null);
        livingPlaceInfoRep.deleteByPersonInLivingPlaceInfo(person);
    }

    @Override
    public void deleteLivingPlaceInfoByPerson(Person person) {
        livingPlaceInfoRep.deleteByPersonInLivingPlaceInfo(person);
    }


    @Transactional
    @Override
    public Person findLinkedPerson(long livingPlaceInfoId) {
        LivingPlaceInfo livingPlaceInfo = livingPlaceInfoRep.findById(livingPlaceInfoId).orElse(new LivingPlaceInfo());
        return personRep.findByLivingPlaceInfo(livingPlaceInfo).orElse(null);
    }

    @Override
    public Person findLinkedPerson(LivingPlaceInfo livingPlaceInfo) {
        return personRep.findByLivingPlaceInfo(livingPlaceInfo).orElse(null);
    }
}
