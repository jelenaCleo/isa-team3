package com.ftn.fishingbooker.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VacationHome {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean deleted = false;

    private Integer guestLimit;

    private String description;

    private double rating = 0.0;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    private Address address;

    @Column
    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
    private Set<String> codeOfConduct;

    @OneToMany(targetEntity = Utility.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Utility> utilities;

    @OneToMany(targetEntity = Image.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Image> images;

    @OneToMany(targetEntity = Reservation.class, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Set<Reservation> availableReservations;

    @OneToMany(targetEntity = Room.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Room> rooms;

    @ManyToOne(targetEntity = HomeOwner.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "home_owner_id")
    private HomeOwner homeOwner;

}
