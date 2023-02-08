package com.example.cloneprojectsoc.security.service;

import com.example.cloneprojectsoc.security.entity.Permission;
import com.example.cloneprojectsoc.security.entity.User;
import com.example.cloneprojectsoc.security.repository.PermissionRepository;
import com.example.cloneprojectsoc.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImp implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            log.error("user does not exist");
            throw new UsernameNotFoundException("user not found in db");
        }else {
            log.info("user is exist in db");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getPermissions().forEach(permission ->
                authorities.add(new SimpleGrantedAuthority(permission.getPermissionName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void addPermissionToUser(String username, String permissionName) {
        User user = userRepository.findByUsername(username);
        Permission permission = permissionRepository.findByPermissionName(permissionName);
        user.getPermissions().add(permission);

    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

}
