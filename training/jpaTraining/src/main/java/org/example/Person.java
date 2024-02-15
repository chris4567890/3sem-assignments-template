package org.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table (name = "person")
@Getter
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "email", nullable = false,unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    public enum Gender {
        MALE,
        FEMALE
    }
    public Person(String firstname,String email, Gender gender){
        this.firstname = firstname;
        this.gender = gender;
        this.email = email;
    }
    public Person(){

    }
}
