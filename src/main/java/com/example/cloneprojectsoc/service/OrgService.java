package com.example.cloneprojectsoc.service;

import com.example.cloneprojectsoc.repository.OrgRepository;
import org.springframework.stereotype.Service;

@Service
public class OrgService {
    private final OrgRepository orgRepository;

    public OrgService(OrgRepository orgRepository) {
        this.orgRepository = orgRepository;
    }
}
