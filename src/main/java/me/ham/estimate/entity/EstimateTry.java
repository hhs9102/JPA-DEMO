package me.ham.estimate.entity;

import lombok.Setter;

import javax.persistence.*;

@Entity(name = "estimate_try")
@Setter
public class EstimateTry {

    @Id
    @GeneratedValue
    @Column(name = "estimate_try_id")
    private Integer estimateTryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estimate_id")
    private Estimate estimate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dealer_id")
    private Dealer dealer;

    @Column(name = "price")
    private Integer price;


}
