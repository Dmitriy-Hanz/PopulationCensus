package populationcensus.service.dto.mapper;

import org.mapstruct.Mapper;
import populationcensus.repository.entity.Household;
import populationcensus.service.dto.HouseholdDto;

@Mapper(componentModel = "spring")
public interface HouseholdMapper {
    HouseholdDto toHouseholdDto(Household horse);

    Household toHousehold(HouseholdDto horseDto);
}
