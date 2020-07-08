package me.ham.identity.embeddable;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "parent_embeddable")
@Getter
public class Parent  extends Person {

    @Id @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;
}
