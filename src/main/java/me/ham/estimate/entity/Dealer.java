package me.ham.estimate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "dealer")
@Setter
@Getter
public class Dealer {
    @Id
    @GeneratedValue
    @Column(name = "dealer_id")
    private Integer dealerId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "dealer")
    private List<EstimateTry> estimateTries = new ArrayList<>();
}
