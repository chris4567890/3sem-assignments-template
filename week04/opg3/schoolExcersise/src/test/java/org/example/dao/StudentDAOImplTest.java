package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.HibernateConfig;
import org.example.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDAOImplTest {



    @Test
    void findAllStudentsByFirstName() {
        StudentDAOImpl studentDAO = new StudentDAOImpl();
        List<Student> students = studentDAO.findAllStudentsByFirstName("patrick");
        assertNotNull(students);
    }

    @Test
    void findAllStudentsByLastName() {
        StudentDAOImpl studentDAO = new StudentDAOImpl();
        List<Student> students = studentDAO.findAllStudentsByLastName("the awesome");
        assertNotNull(students);
    }

    @Test
    void findTotalNumberOfStudentsBySemester() {
        StudentDAOImpl studentDAO = new StudentDAOImpl();
        long i = studentDAO.findTotalNumberOfStudentsBySemester("math");
        assertNotNull(i);
    }

    @Test
    void findTotalNumberOfStudentsByTeacher() {
    }

    @Test
    void findTeacherWithMostSemesters() {
    }

    @Test
    void findSemesterWithFewestStudents() {
    }

    @Test
    void getAllStudentInfo() {
    }
}