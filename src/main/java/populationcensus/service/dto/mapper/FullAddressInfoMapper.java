package populationcensus.service.dto.mapper;

import org.mapstruct.Mapper;
import populationcensus.repository.entity.ChildrenInfo;
import populationcensus.repository.entity.FullAddressInfo;
import populationcensus.service.dto.ChildrenInfoDto;
import populationcensus.service.dto.FullAddressInfoDto;
import populationcensus.service.dto.HouseholdDto;

@Mapper(componentModel = "spring")
public interface FullAddressInfoMapper {
    FullAddressInfoDto toFullAddressInfoDto(FullAddressInfo entity);

    FullAddressInfo toFullAddressInfo(FullAddressInfoDto entityDto);
}
