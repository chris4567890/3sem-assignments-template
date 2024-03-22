package org.example.entities;

import io.javalin.security.RouteRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Role {
    @Id
    private String role;

    public Role(String role){
        this.role = role;
    }

    enum RoleEnum  {
        ANYONE,
        USER,
        ADMIN
    }


}
