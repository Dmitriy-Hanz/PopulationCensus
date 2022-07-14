package populationcensus.service.dto.mapper;

import org.mapstruct.Mapper;
import populationcensus.repository.entity.LivingCountryInfo;
import populationcensus.service.dto.LivingCountryInfoDto;

@Mapper(componentModel = "spring")
public interface LivingCountryInfoMapper {
    LivingCountryInfoDto toLivingCountryInfoDto(LivingCountryInfo entity);

    LivingCountryInfo toLivingCountryInfo(LivingCountryInfoDto entityDto);
}
