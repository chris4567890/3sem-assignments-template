package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.Role;
import org.example.User;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class UserDTO {
    private Role role;
    private User user;

    public UserDTO(User user){
        this.user = user;
        this.role = (Role) user.getRolesAsStrings();
    }
    public UserDTO(Role role){
        this.role = role;
    }
    Set<Role> roles = new HashSet<>();
    @JsonIgnore
    public Set<String> getRolesAsString(){
        if(user.getRoles().isEmpty()){
            return null;
        }
        Set<String> rolesAsString = new HashSet<>();
        roles.forEach((role) -> {
            rolesAsString.add(role.getRole());
        });
        return rolesAsString;
    }


}
