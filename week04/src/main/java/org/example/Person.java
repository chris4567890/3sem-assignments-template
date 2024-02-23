package org.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;



    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private PersonDetail personDetail;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Fee> fees = new HashSet<>();
    public Person(String name){
        this.name = name;
    }
    public void addFee(Fee fee){

        if(fee != null){
            fee.setPerson(this);

            fees.add(fee);
        }
    }
    public void addPersonDetail(PersonDetail personDetail){
        this.personDetail = personDetail;
        if(personDetail != null){
            personDetail.setPerson(this);
        }
    }


}
