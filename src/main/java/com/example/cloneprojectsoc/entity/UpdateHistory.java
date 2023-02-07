package com.example.cloneprojectsoc.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "update_history_table")
public class UpdateHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int historyId;

    private int orgId;

    private int socId;

}
