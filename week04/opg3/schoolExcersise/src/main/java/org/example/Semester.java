package org.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    @ManyToMany
    private Set<Student> students = new HashSet<>();
    @ManyToMany
    private Set<Teacher> teachers = new HashSet<>();
    public void add_teacher(Teacher teacher){
        this.teachers.add(teacher);
    }
    public void remove_teacher(Teacher teacher){
        this.teachers.remove(teacher);
    }
    public void add_students(Student student){
        this.students.add(student);
    }

    public void remove_students(Student student){
        this.students.remove(student);
    }

    public Semester(String name,String description){
        this.name = name;
        this.description = description;
    }



}
