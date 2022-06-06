package repository.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "household")
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "number_of_members")
    private Integer numberOfMembers;

    @Column(name = "rooms_count")
    private Integer roomsCount;

    @Column(name = "part_of_room")
    private String partOfRoom;

    @Column(name = "has_foreign_residents")
    private Boolean hasForeignResidents;

    @Column(name = "has_farm_facilities")
    private Boolean hasFarmFacilities;

    @OneToOne(mappedBy = "householdInFullAddressInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private FullAddressInfo fullAddressInfo;

    @OneToOne(mappedBy = "householdInAccommodationsInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AccommodationsInfo accommodationsInfo;

    @OneToMany(mappedBy = "householdField", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Person> persons;
}
