package populationcensus.dto.mapper;

import org.mapstruct.*;
import populationcensus.repository.entity.Person;
import populationcensus.dto.PersonDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LivingPlaceInfoMapper.class, LivingCountryInfoMapper.class, EducationInfoMapper.class, WorkInfoMapper.class, ChildrenInfoMapper.class})
public interface PersonMapper {
    PersonDto toPersonDto(Person entity);

    Person toPerson(PersonDto entityDto);


    List<Person> toPersonList(List<PersonDto> entityDtoList);

    List<PersonDto> toPersonDtoList(List<Person> entityList);
}
