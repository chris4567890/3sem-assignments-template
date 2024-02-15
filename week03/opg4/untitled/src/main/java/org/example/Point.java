package org.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "x")
    private int x;
    @Column(name = "y")
    private int y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Point(){

    }

}
