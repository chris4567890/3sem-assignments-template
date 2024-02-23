package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.example.HibernateConfig;
import org.example.Semester;
import org.example.Student;
import org.example.Teacher;

import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO{
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("school_opg3");
    @Override
    public List<Student> findAllStudentsByFirstName(String firstName) {

        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select s.first_name from Student s");
        return query.getResultList();
    }

    @Override
    public List<Student> findAllStudentsByLastName(String lastName) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select s.last_name from Student s");
        return query.getResultList();
    }

    @Override
    public long findTotalNumberOfStudentsBySemester(String semesterName) {

        long i = 0;
        List<Student> students;
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select Student from Semester where name = :name");
        query.setParameter("name",semesterName);
        students = query.getResultList();
        for(Student s:students){
            ++i;
        }
        return i;
    }

    @Override
    public long findTotalNumberOfStudentsByTeacher(Teacher teacher) {
        EntityManager em = emf.createEntityManager();
        //based largely on code from here https://stackoverflow.com/questions/11914305/how-to-count-students-and-classes-of-every-teacher-in-sql-server
        Query query = em.createQuery("select count(distinct c) from Semester s join s.teachers t join s.students c where t.id =:id");
        query.setParameter("id",teacher.getId());
        long result = (long) query.getSingleResult();
        return result;
    }
    //code from here https://stackoverflow.com/questions/54167720/finding-the-entity-with-the-most-relationships-in-jpql
    @Override
    public Teacher findTeacherWithMostSemesters() {
        try(EntityManager em = emf.createEntityManager()){
            Query query = em.createQuery("select t,count(*) as c from Teacher t join Semester  s group by s.id order by c desc limit 1");
            Teacher teacher = (Teacher) query.getSingleResult();
            return teacher;
        }
    }

    @Override
    public Semester findSemesterWithFewestStudents() {
        try(EntityManager em = emf.createEntityManager()){
            Query query = em.createQuery("select s,count(*) as c from Semester s join Student p order by c desc limit 1");
            Semester semester = (Semester) query.getSingleResult();
            return semester;
        }
    }

    @Override

    public StudentInfo getAllStudentInfo(Integer id) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Query queryStudent = em.createQuery("select s from Student s where s.id = :id");
            Query getSemesterNames = em.createQuery("select s.name from Semester s join Student p where p.id = :id");
            getSemesterNames.setParameter("id",id);
            Query getSemesterDescription = em.createQuery("select s.description from Semester s join Student p where p.id = :id");
            getSemesterDescription.setParameter("id",id);
            Student student = (Student) queryStudent.getSingleResult();
            String full_name = student.getFirst_name() + " " + student.getLast_name();
            List<String> semesterNames = getSemesterNames.getResultList();
            List<String> semesterDescription = getSemesterDescription.getResultList();
            StudentInfo studentInfo = new StudentInfo(full_name,id,semesterNames,semesterDescription);

            return studentInfo;
        }
    }
}
