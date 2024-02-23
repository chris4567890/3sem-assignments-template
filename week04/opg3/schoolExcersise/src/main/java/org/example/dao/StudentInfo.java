package org.example.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.example.HibernateConfig;
import org.example.Semester;
import org.example.Student;

import java.util.List;

public class StudentInfo {
    private String fullName;
    private Integer id;
    private List<String> thisSemesterName;
    private List<String> thisSemesterDescription;


    public StudentInfo(String fullname,Integer id,List<String> thisSemesterName,List<String> thisSemesterDescription){
        this.fullName = fullname;
        this.id = id;
        this.thisSemesterName = thisSemesterName;
        this.thisSemesterDescription = thisSemesterDescription;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getThisSemesterName() {
        return thisSemesterName;
    }

    public void setThisSemesterName(List<String> thisSemesterName) {
        this.thisSemesterName = thisSemesterName;
    }

    public List<String> getThisSemesterDescription() {
        return thisSemesterDescription;
    }

    public void setThisSemesterDescription(List<String> thisSemesterDescription) {
        this.thisSemesterDescription = thisSemesterDescription;
    }
}
