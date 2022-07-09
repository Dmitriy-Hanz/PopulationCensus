package populationcensus.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import populationcensus.repository.entity.LivingCountryInfo;
import populationcensus.repository.entity.LivingPlaceInfo;

import javax.persistence.Column;
import java.util.Date;

@Data
@NoArgsConstructor
public class LivingCountryInfoDto {
    private Long id;
    private Boolean didYouLiveInOtherCountry;
    private String nameOfCountryYouCameFrom;
    private Date arrivalPeriod;
    private Integer reasonForArrivalAtRB;
    private Integer infoAboutLeavingBelarus;
    private Integer reasonForLeavingBelarus;

    public LivingCountryInfoDto(LivingCountryInfo entity) {
        id = entity.getId();
        didYouLiveInOtherCountry = entity.getDidYouLiveInOtherCountry();
        nameOfCountryYouCameFrom = entity.getNameOfCountryYouCameFrom();
        arrivalPeriod = entity.getArrivalPeriod();
        reasonForArrivalAtRB = entity.getReasonForArrivalAtRB();
        infoAboutLeavingBelarus = entity.getInfoAboutLeavingBelarus();
        reasonForLeavingBelarus = entity.getReasonForLeavingBelarus();
    }
}
