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

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getOverallexp() {
        return overallexp;
    }

    public void setOverallexp(int overallexp) {
        this.overallexp = overallexp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
