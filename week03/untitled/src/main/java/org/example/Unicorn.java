package org.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Unicorn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;
    @Column(name="name",nullable = false)
    private String name;

    @Column(name="age",nullable = false)
    private int age;

    @Column (name="powerStrength")
    private int powerStrength;

    public Unicorn(String name,int age,int powerStrength){
        this.name = name;
        this.age = age;
        this.powerStrength = powerStrength;
    }
    public Unicorn(){

    }

}
