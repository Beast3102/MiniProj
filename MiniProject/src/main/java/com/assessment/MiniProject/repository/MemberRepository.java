package com.assessment.MiniProject.repository;

import com.assessment.MiniProject.model.Membermodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Membermodel,Integer> {
    @Query(value = "SELECT EID FROM MEMBERS WHERE location=:loc AND status=:status AND level=:level", nativeQuery = true)
    public List<Integer> getMemberEid(@Param("loc")String loc, @Param("status")String status, @Param("level")String level);

    @Query(value = "SELECT EID FROM (SELECT * FROM SKILL_TABLE WHERE SKILLS = :skill)",nativeQuery = true)
    public List<Integer> getMemberWithSkill(@Param("skill")String skill);
}
