package com.assessment.MiniProject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "Members")
@Data
public class Membermodel {
    @Id
    private int eid;
    private String firstname;
    private String lastname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date doj;
    private String level;
    private String location;
    private int overallexp;
    private String status;

    @ElementCollection
    @CollectionTable(name = "skill_table", joinColumns = @JoinColumn(name = "eid"))
    @MapKeyColumn(name = "skills")
    @Column(name = "experience")
    private Map<String,Integer> skills;
}
