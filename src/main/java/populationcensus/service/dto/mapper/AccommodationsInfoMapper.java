package populationcensus.service.dto.mapper;

import org.mapstruct.Mapper;
import populationcensus.repository.entity.AccommodationsInfo;
import populationcensus.repository.entity.ChildrenInfo;
import populationcensus.service.dto.AccommodationsInfoDto;
import populationcensus.service.dto.ChildrenInfoDto;
import populationcensus.service.dto.HouseholdDto;

@Mapper(componentModel = "spring")
public interface AccommodationsInfoMapper {
    AccommodationsInfoDto toAccommodationsInfoDto(AccommodationsInfo entity);

    AccommodationsInfo toAccommodationsInfo(AccommodationsInfoDto entityDto);
}
