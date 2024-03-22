package org.example.entities;

import jakarta.persistence.*;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashSet;
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
    @Setter(AccessLevel.NONE)
    @JoinTable(name = "users_role",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "roles_role", referencedColumnName = "role")
            },uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "roles_role"})
    )
    @ManyToMany
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //<String> roles = new HashSet<>();
    public Set<String> getRolesAsStrings() {
        if (roles.isEmpty()) {
            return null;
        }
        Set<String> rolesAsStrings = new HashSet<>();
        roles.forEach((role) -> {
            rolesAsStrings.add(role.getRole());
        });
        return rolesAsStrings;
    }

    public void addRole(Role role) {
        roles.add(role);
    }


    public boolean verifyPassword(String pass) {
        return BCrypt.checkpw(pass, this.password);
    }


}
