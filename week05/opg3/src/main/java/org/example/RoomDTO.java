package org.example;

import lombok.*;
import org.intellij.lang.annotations.Identifier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int number;
    double price;



}
