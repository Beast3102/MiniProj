package com.assessment.MiniProject.repository;

import com.assessment.MiniProject.model.DemandModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandRepository extends JpaRepository<DemandModel, Integer> {
}
