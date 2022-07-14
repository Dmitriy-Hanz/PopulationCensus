package populationcensus.service.dto.mapper;

import org.mapstruct.Mapper;
import populationcensus.repository.entity.ChildrenInfo;
import populationcensus.repository.entity.WorkInfo;
import populationcensus.service.dto.ChildrenInfoDto;
import populationcensus.service.dto.HouseholdDto;
import populationcensus.service.dto.WorkInfoDto;

@Mapper(componentModel = "spring")
public interface WorkInfoMapper {
    WorkInfoDto toWorkInfoDto(WorkInfo entity);

    WorkInfo toWorkInfo(WorkInfoDto entityDto);
}
