package com.example.cloneprojectsoc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@Table(name = "soc_table")
public class Soc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int socId;

    @Column(name = "soc_name")
    private String socName;

    private String token;

    private int restrictInterval;

    @ManyToMany
    @JoinTable(name = "org_soc", joinColumns = @JoinColumn(name = "soc_id"),inverseJoinColumns = @JoinColumn(name = "org_id"))
    @JsonBackReference
    private Set<Orgnization> orgnizations;

    public Soc() {

    }
}
