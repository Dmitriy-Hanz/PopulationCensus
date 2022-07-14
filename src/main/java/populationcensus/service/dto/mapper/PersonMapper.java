package populationcensus.service.dto.mapper;

import org.mapstruct.Mapper;
import populationcensus.repository.entity.Household;
import populationcensus.repository.entity.Person;
import populationcensus.service.dto.HouseholdDto;
import populationcensus.service.dto.PersonDto;

@Mapper(componentModel = "spring", uses = {LivingPlaceInfoMapper.class, LivingCountryInfoMapper.class, EducationInfoMapper.class, WorkInfoMapper.class, ChildrenInfoMapper.class})
public interface PersonMapper {
    PersonDto toPersonDto(Person entity);

    Person toPerson(PersonDto entityDto);
}
