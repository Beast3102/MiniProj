package com.assessment.MiniProject.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Mapped")
@Data
public class MappedModel {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private int project_id;
    private int eid;

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }
}
