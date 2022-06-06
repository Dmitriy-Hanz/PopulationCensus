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
@Table(name = "living_place_info")
public class LivingPlaceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "do_you_live_here_from_birth")
    private Boolean doYouLiveHereFromBirth;

    @Column(name = "arrival_period")
    private Date arrivalPeriod;

    @Column(name = "previous_living_place")
    private Integer previousLivingPlace;

    @Column(name = "region_or_district_name")
    private String regionOrDistrictName;

    @Column(name = "city_or_pgt_name")
    private String cityOrPGTName;

    @Column(name = "is_it_village")
    private Boolean isItVillage;

    @Column(name = "name_of_previous_country")
    private String nameOfPreviousCountry;

    @Column(name = "reason_for_arrival_at_place")
    private Integer reasonForArrivalAtPlace;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Person personInLivingPlaceInfo;
}
