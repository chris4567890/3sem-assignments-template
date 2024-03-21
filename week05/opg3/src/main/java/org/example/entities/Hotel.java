package org.example.entities;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Room> rooms = new ArrayList<>();

    public void add_room(Room room){
        rooms.add(room);
    }

    public Hotel(String name,String address){
        this.name = name;
        this.address = address;
    }

}
