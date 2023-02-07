package com.example.cloneprojectsoc.repository;

import com.example.cloneprojectsoc.entity.Orgnization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgRepository extends JpaRepository<Orgnization, Integer> {
}
