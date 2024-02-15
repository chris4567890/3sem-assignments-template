package org.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static org.hibernate.engine.jdbc.Size.LobMultiplier.G;

@Entity
@Getter
@Setter
@ToString
public class Student {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id",nullable = false)
private int id;

@Column(name = "first_name",length = 100)
private String first_name;

@Column(name ="last_name",length = 100)
private String last_name;

@Column (name = "email",unique = true)
private String email;

@Column (name = "age")
private int age;

    public Student(String first_name,String last_name, String email,int age){
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.age = age;
    }
    public Student(){

    }
    @PrePersist
    public void prePersist(){
        if(email.contains("@") == false){
            throw new RuntimeException("does not contain an email tough shit");
        }
    }

}
