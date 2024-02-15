package org.example;

public class Main {
    public static void main(String[] args) {
       // System.out.println("Hello world!");
       //HibernateConfig.getEntityManagerFactoryConfig();
        Unicorn nicklas = new Unicorn("Nicklas",40,40);
        Unicorn jörg = new Unicorn("jörg",21,3000);
        Unicorn fantastiskTutor = new Unicorn("fantastisk tutor",20,9001);
        UnicornDao unicornDao = new UnicornDao();
        unicornDao.save(fantastiskTutor);
        //System.out.println(unicornDao.findAll());
        //unicornDao.save(jörg);
        //System.out.println(unicornDao.findById(2));
        //unicornDao.delete(2);
        //System.out.println(unicornDao.findAll());
    }
}