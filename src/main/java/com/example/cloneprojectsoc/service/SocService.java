package com.example.cloneprojectsoc.service;

import com.example.cloneprojectsoc.repository.SocRepository;
import org.springframework.stereotype.Service;

@Service
public class SocService {
    private final SocRepository socRepository;

    public SocService(SocRepository socRepository) {
        this.socRepository = socRepository;
    }
}
