package populationcensus.service.dto.mapper;

import org.mapstruct.Mapper;
import populationcensus.repository.entity.ChildrenInfo;
import populationcensus.repository.entity.Household;
import populationcensus.service.dto.ChildrenInfoDto;
import populationcensus.service.dto.HouseholdDto;

@Mapper(componentModel = "spring")
public interface ChildrenInfoMapper {
    ChildrenInfoDto toChildrenInfoDto(ChildrenInfo entity);

    ChildrenInfo toChildrenInfo(ChildrenInfoDto entityDto);
}
