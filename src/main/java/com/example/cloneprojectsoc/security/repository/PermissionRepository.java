package com.example.cloneprojectsoc.security.repository;

import com.example.cloneprojectsoc.security.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    Permission findByPermissionName(String permissionName);
}
