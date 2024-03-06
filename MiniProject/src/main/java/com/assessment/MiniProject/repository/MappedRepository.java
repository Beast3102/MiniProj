package com.assessment.MiniProject.repository;

import com.assessment.MiniProject.model.MappedModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MappedRepository extends JpaRepository<MappedModel,Integer> {
}
