package com.example.cloneprojectsoc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@Table(name = "orgnization_table")
public class Orgnization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orgId;

    @Column(name = "org_name")
    private String orgName;


    @ManyToMany(mappedBy = "orgnizations")
    @JsonBackReference
    private Set<Soc> socs;

    public Orgnization() {

    }
}
