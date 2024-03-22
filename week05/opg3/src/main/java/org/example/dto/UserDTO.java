package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.entities.Role;
import org.example.entities.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class UserDTO {
    private Set<String> roles = new HashSet<>();
    private String username;

    public UserDTO(User user){
        this.username = user.getUsername();
        this.roles = user.getRolesAsStrings();
    }
    public UserDTO(Role role){
        this.roles.add(role.getRole());
    }

    /*public UserDTO(String username, Set<Role> rolesSet) {
        user.setUsername(username);
        for(Role role: rolesSet){
            user.addRole(role);

        }
    }*/

    public UserDTO(String username, Set<String> rolesSet) {

    }


    @JsonIgnore
    public Set<String> getRolesAsString(){
        if(roles.isEmpty()){
            return null;
        }
        Set<String> rolesAsString = new HashSet<>();
        rolesAsString.addAll(roles);
        return rolesAsString;
    }



}
