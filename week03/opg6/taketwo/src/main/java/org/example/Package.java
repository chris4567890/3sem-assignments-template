package org.example;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@Data
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;
    @Column(name="tracking_number",nullable = false)
    private String tracking_number;
    @Column(name = "sender",nullable = false)
    private String sender;
    @Column(name = "receiver",nullable = false)
    private String receiver;
    @Enumerated(EnumType.STRING)
    private Delivery_status delivery_status;

    public Package(String tracking_number,String sender,String receiver, Delivery_status status)
    {
        this.tracking_number =tracking_number;
        this.sender = sender;
        this.receiver = receiver;
        this.delivery_status = status;
    }
    public Package(){

    }

}
