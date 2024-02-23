package org.example;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fee_id")
    private Integer id;
    private LocalDate dueDate;
    private double fee;
    @ManyToOne
    @MapsId
    private Person person;

    public Fee(LocalDate dueDate,int fee){
        this.dueDate = dueDate;
        this.fee = fee;
    }



}
