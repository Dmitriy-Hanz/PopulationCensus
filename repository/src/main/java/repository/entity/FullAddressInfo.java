package repository.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "full_address_info")
public class FullAddressInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "city")
    private String city;

    @Column(name = "city_district")
    private String cityDistrict;

    @Column(name = "region")
    private String region;

    @Column(name = "region_district")
    private String regionDistrict;

    @Column(name = "village_council")
    private String villageCouncil;

    @Column(name = "village_name")
    private String villageName;

    @Column(name = "street_avenue_other")
    private String streetAvenueOther;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "owner_fio")
    private String ownerFIO;

    @Column(name = "korp_number")
    private String frameNumber;

    @Column(name = "flat_or_room_number")
    private String flatOrRoomNumber;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Household householdInFullAddressInfo;
}
