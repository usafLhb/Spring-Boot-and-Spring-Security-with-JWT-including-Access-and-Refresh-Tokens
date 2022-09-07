package io.getarrays.userservice.service;

import io.getarrays.userservice.domaine.Role;
import io.getarrays.userservice.domaine.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User>getUsers();


}
