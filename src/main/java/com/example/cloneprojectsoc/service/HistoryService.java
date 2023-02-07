package com.example.cloneprojectsoc.service;

import com.example.cloneprojectsoc.repository.HistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }
}
