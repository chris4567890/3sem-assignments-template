package org.example;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class AppointmentDTO {

    private Integer id;
    private Date date;

    PatientDTO patient;


}
