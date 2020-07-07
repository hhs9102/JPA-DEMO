package me.ham.identity;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class Person {
    private String name;
    private Integer age;
}
