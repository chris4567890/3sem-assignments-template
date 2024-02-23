package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Note {
    @Id
    private Integer id;
    private String note;
    private LocalDate created;
    private String createdBy;



    public Note(String note, LocalDate created, String createdBy){
        this.note = note;
        this.created = created;
        this.createdBy = createdBy;
    }

}
