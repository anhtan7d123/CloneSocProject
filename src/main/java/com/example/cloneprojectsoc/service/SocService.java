package com.example.cloneprojectsoc.service;

import com.example.cloneprojectsoc.entity.Soc;
import com.example.cloneprojectsoc.repository.SocRepository;
import org.springframework.stereotype.Service;

@Service
public class SocService {
    private final SocRepository socRepository;

    public SocService(SocRepository socRepository) {
        this.socRepository = socRepository;
    }
    public Soc findBySocName(String socName){
        return socRepository.findSocByName(socName);
    }
}
