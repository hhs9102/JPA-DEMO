package me.ham.estimate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "estimate")
@Setter
@Getter
public class Estimate {

    @Id
    @GeneratedValue
    @Column(name = "estimate_id")
    private Integer estimateId;

    @Column(name = "car_number")
    private String carNumber;

    @OneToMany(mappedBy = "estimate")
    private List<EstimateTry> estimateTries = new ArrayList<>();
}
