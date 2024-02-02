package org.app;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Date;

public class Employee {

    private LocalDate birthday;

    private String name;



    public Employee(String name,LocalDate birthday ){
        this.name = name;
        this.birthday = birthday;
    }

    public LocalDate getBirthday(){
        return birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Month getBirthMonth(){
        Month monthofBirthday = birthday.getMonth();
        return monthofBirthday;
    }

    public Period calculateBirthday(LocalDate birthday){
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(birthday,currentDate);
        return age;
    }



    @Override
    public String toString() {
        return "Employee{" +
                "birthday=" + birthday +
                ", name='" + name + '\'' +
                '}';
    }
}
