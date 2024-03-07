package org.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class HotelDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    @OneToMany

    private List<RoomDTO> rooms = new ArrayList<>();



}
