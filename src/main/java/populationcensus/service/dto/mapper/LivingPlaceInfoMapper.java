package populationcensus.service.dto.mapper;

import org.mapstruct.Mapper;
import populationcensus.repository.entity.LivingPlaceInfo;
import populationcensus.repository.entity.WorkInfo;
import populationcensus.service.dto.LivingPlaceInfoDto;
import populationcensus.service.dto.WorkInfoDto;

@Mapper(componentModel = "spring")
public interface LivingPlaceInfoMapper {
    LivingPlaceInfoDto toLivingPlaceInfoDto(LivingPlaceInfo entity);

    LivingPlaceInfo toLivingPlaceInfo(LivingPlaceInfoDto entityDto);
}
