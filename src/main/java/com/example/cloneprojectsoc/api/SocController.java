package com.example.cloneprojectsoc.api;

import com.example.cloneprojectsoc.entity.Orgnization;
import com.example.cloneprojectsoc.entity.Soc;
import com.example.cloneprojectsoc.repository.OrgRepository;
import com.example.cloneprojectsoc.repository.SocRepository;
import com.example.cloneprojectsoc.service.SocService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/api")
public class SocController {

    SocRepository socRepository;

    SocService socService;

    @PostMapping("/soc/create-soc")
    public ResponseEntity<?> createSoc(@RequestBody Soc soc){
        Soc newSoc = new Soc();
        newSoc.setSocName(soc.getSocName());
        newSoc.setRestrictInterval(soc.getRestrictInterval());
        Set<Orgnization> orgnizations = new HashSet<>();
        soc.getOrgnizations().forEach(orgnization -> orgnizations.add(orgnization));
        newSoc.setOrgnizations(orgnizations);
        Soc checkSoc = socService.findBySocName(newSoc.getSocName());
        if(checkSoc != null){
            log.error("socName is exist");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            socRepository.save(newSoc);
            return new ResponseEntity<>(newSoc, HttpStatus.CREATED);
        }
    }

    @GetMapping("/soc/get-all")
    public ResponseEntity<?> getAllSoc(){
        return new ResponseEntity<>(socRepository.findAll(), HttpStatus.OK);
    }
}
