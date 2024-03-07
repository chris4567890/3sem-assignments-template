package org.example;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Appointment {

    private int id;
    private Date date;

    private String name;

}
