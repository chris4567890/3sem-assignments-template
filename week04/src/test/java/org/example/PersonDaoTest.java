package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonDaoTest {
    EntityManagerFactory emf = HibernateConfigerTest.getEntityManagerFactoryConfig();
    Person person = new Person("test");
    PersonDetail personDetail = new PersonDetail("test",4,"test",4);

    PersonDao personDao = new PersonDao();

    @Test
    void createPerson() {
        person.addPersonDetail(personDetail);
        assertTrue(personDao.createPerson(person));
    }

    @Test
    void findPerson() {
        person.addPersonDetail(personDetail);
        personDao.createPerson(person);
        assertNotNull(personDao.findPerson(1));
    }

    @Test
    void deletePerson() {
        assertTrue(personDao.deletePerson(1));
    }

}