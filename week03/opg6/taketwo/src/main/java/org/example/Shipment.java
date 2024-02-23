package org.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    Package packages = new Package();
    @ManyToOne
    Location source = new Location();
    @ManyToOne
    Location destination = new Location();
    Date shipment = new Date();

    public Shipment(Package packages, Location source,Location destination,Date shipment){
        this.packages  = packages;
        this.source = source;
        this.destination = destination;
        this.shipment = shipment;
    }



}
