package com.tekup.realestateapi.models;


import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "realestate_id", nullable = false)
    private RealEstate realEstate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingDate;
    

    @Enumerated(EnumType.STRING)
    @Column(name = "state_booking")
    private StateReservation stateReservation;

    @Column(name = "number_of_days_or_months")
    private Integer numberOfDaysOrMonths;

}
