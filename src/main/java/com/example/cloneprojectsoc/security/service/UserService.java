package com.example.cloneprojectsoc.security.service;

import com.example.cloneprojectsoc.security.entity.Permission;
import com.example.cloneprojectsoc.security.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    Permission savePermission(Permission permission);

    void addPermissionToUser(String username, String permissionName);

    User getUserByUsername(String username);

    List<User> getAllUser();

}
