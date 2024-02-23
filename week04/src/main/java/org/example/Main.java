package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

        Person nicklas = new Person("Nicklas");
        LocalDate dueFee = LocalDate.of(2024,5,6);
        Fee subscription = new Fee(dueFee,87);
        Fee non_refundable = new Fee (dueFee,160);
        //nicklas.addFee(subscription);
        PersonDetail nicklasDetail = new PersonDetail("børgeland 8",4848,"Rudersdal",22);
        nicklas.addPersonDetail(nicklasDetail);
        nicklas.addFee(subscription);
        PersonDao personManager = new PersonDao();
        //personManager.createPerson(nicklas);
        //personManager.deletePerson(7);
        System.out.println(personManager.findPerson(8));
        //FeeDao fm = new FeeDao();
        //System.out.println(fm.findAllFeeBelongingToThisPerson(7));
        //fm.findFee(4);
        //System.out.println(personManager.findPerson(7));
        //System.out.println(fm.findFee(1));
        //System.out.println(personManager.findPerson(1));
        //personManager.createPerson(nicklas);
        //personManager.addFees(7,non_refundable);
        //personManager.deletePerson(6);
        //personManager.createPerson(nicklas);
        //personManager.createPerson(nicklas);


    }
}