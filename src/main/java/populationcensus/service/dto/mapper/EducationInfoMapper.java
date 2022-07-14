package populationcensus.service.dto.mapper;

import org.mapstruct.Mapper;
import populationcensus.repository.entity.EducationInfo;
import populationcensus.repository.entity.WorkInfo;
import populationcensus.service.dto.EducationInfoDto;
import populationcensus.service.dto.WorkInfoDto;

@Mapper(componentModel = "spring")
public interface EducationInfoMapper {
    EducationInfoDto toEducationInfoDto(EducationInfo entity);

    EducationInfo toEducationInfo(EducationInfoDto entityDto);
}
