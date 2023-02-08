package com.example.cloneprojectsoc.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@Table(name = "Permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String permissionName;

    @ManyToMany(mappedBy = "permissions")
    private Collection<User> users;

    public Permission() {

    }
}
