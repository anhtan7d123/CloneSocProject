package com.example.cloneprojectsoc.security.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    private String password;

    @ManyToMany
    @JoinTable(name = "user_permission",
            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    @JsonBackReference
    private Collection<Permission> permissions;

    public User() {
        
    }

    public User(String username, String password, Collection<Permission> permissions) {
        this.username = username;
        this.password = password;
        this.permissions = permissions;
    }
}
