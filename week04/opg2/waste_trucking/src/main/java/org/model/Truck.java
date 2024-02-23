package org.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @OneToMany(mappedBy = "truck",fetch= FetchType.EAGER)
    List<Driver> drivers;
    private String brand;
    private int capacity;
    private boolean is_available;
    private String registration_number;

    public void add_driver(Driver driver){
        drivers.add(driver);
    }

    public Truck(String brand,int capacity, String registration_number){
        this.brand = brand;
        this.capacity = capacity;
        this.registration_number = registration_number;
    }



}
