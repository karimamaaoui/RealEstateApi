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
@Table(name = "payment") 
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPay;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDePaiement;

    private float price;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_payment")
    private TypePayment typePayment;

}
