package io.getarrays.userservice.service;

import io.getarrays.userservice.entity.Role;
import io.getarrays.userservice.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User>getUsers();


}
