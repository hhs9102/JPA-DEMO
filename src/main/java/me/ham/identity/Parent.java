package me.ham.identity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Parent  extends Person{

    @Id @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;
}
