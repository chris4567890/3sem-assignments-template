package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.entities.Role;
import org.example.entities.User;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class UserDTO {
    private Set<String> roles = new HashSet<>();
    private User user;

    public UserDTO(User user){
        this.user = user;
        this.roles = user.getRolesAsStrings();
    }
    public UserDTO(Role role){
        this.roles.add(role.getRole());
    }

    public UserDTO(String username, Set<String> rolesSet) {
        user.setUsername(username);
        for(String s: rolesSet){
            user.setRole(s);
        }
    }

    @JsonIgnore
    public Set<String> getRolesAsString(){
        if(user.getRoles().isEmpty()){
            return null;
        }
        Set<String> rolesAsString = new HashSet<>();
        rolesAsString.addAll(roles);
        return rolesAsString;
    }


}
