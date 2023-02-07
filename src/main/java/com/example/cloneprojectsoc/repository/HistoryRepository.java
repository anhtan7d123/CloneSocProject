package com.example.cloneprojectsoc.repository;

import com.example.cloneprojectsoc.entity.UpdateHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<UpdateHistory, Integer> {
}
