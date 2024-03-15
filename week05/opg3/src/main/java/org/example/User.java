package org.example;

import jakarta.persistence.*;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String role;

    @ManyToMany
    List<Role> roles = new ArrayList<>();
    //<String> roles = new HashSet<>();

    public User(String username,String password,String role){
        this.username = username;
        this.password = BCrypt.hashpw(password,BCrypt.gensalt());
        this.role = role;
    }

    public boolean verifyPassword(String pass){
        return BCrypt.checkpw(pass,this.password);
    }




}
