package org.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Entity
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Driver {
    @Id

    private String id;
    @Temporal(TemporalType.DATE)
    private Date employment_date;
    private String name;
    private BigInteger salary;
    private String surname;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "truck_id",referencedColumnName = "id")
    private Truck truck;
    public Driver(String name,String surname,BigInteger salary){
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }
    public void add_truck(Truck truck){
        this.truck = truck;
        truck.getDrivers().add(this);
    }
    public void remove_truck(Truck truck){
        this.truck = null;
        truck.getDrivers().remove(this);
    }

    public Boolean validateDriverId(String id){
        return id.matches("[0-9][0-9][0-9][0-9][0-9][0-9]-[A-Z][A-Z]-[0-9][0-9][0-9][A-Z]");
    }



    @PrePersist
    public void generate_id(){
        Random random =new Random();
        employment_date = new Date();
        DateFormat dateformat = new SimpleDateFormat("dd-MM-yy");
        String strDate = dateformat.format(this.employment_date);
        strDate = strDate.replace("-","");
        String letter_of_first_name = String.valueOf(name.charAt(0));
        String letter_of_last_name = String.valueOf(surname.charAt(0));
        //shameleslly stolen from https://stackoverflow.com/questions/5163785/how-do-i-get-the-last-character-of-a-string
        String last_letter_of_last_name = String.valueOf(surname.charAt(surname.length()-1));
        String last_letter_of_last_name_to_upper_case = last_letter_of_last_name.toUpperCase();
        int random_number_between_100_and_999 = random.nextInt(999)+100;


        String buildid = strDate+"-"+letter_of_first_name+letter_of_last_name+"-"+random_number_between_100_and_999+last_letter_of_last_name_to_upper_case;
        if(validateDriverId(buildid)){
            id = buildid;
        }else {
            id = "";
            System.out.println("id invalid try generate it again");
        }
    }


}
