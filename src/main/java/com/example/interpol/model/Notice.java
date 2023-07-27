package com.example.interpol.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "notices")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notice", updatable = false, nullable = false)
    private Long noticeId;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "id_user")
    private Long userId;
    @Column(name = "name_person", nullable = false)
    private String personName;
    @Column(name = "surname_person", nullable = false)
    private String personSurname;
    @Column(name = "age", nullable = false)
    private int personAge;
    @Column(name = "person_status", nullable = false)
    private String personStatus;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "execution_time", nullable = false)
    private int executionTime;
    @Column(name = "reward", nullable = false)
    private int reward;
    @Column(name = "create_date")
    private Timestamp publicationDateTime;


}
