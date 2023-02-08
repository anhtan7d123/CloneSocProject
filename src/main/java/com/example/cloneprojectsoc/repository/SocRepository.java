package com.example.cloneprojectsoc.repository;

import com.example.cloneprojectsoc.entity.Soc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SocRepository extends JpaRepository<Soc, Integer> {

    @Query("select s from Soc s where s.socName = ?1")
    Soc findSocByName(String socName);
}
