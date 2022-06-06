package repository.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "living_country_info")
public class LivingCountryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "did_you_live_in_other_country")
    private Boolean didYouLiveInOtherCountry;

    @Column(name = "name_of_country_you_came_from")
    private String nameOfCountryYouCameFrom;

    @Column(name = "arrival_period")
    private Date arrivalPeriod;

    @Column(name = "reason_for_arrival_at_rb")
    private Integer reasonForArrivalAtRB;

    @Column(name = "info_about_leaving_belarus")
    private Integer infoAboutLeavingBelarus;

    @Column(name = "reason_for_leaving_belarus")
    private Integer reasonForLeavingBelarus;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Person personInLivingCountryInfo;
}
