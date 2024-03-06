package com.assessment.MiniProject.repository;

import com.assessment.MiniProject.model.DemandModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DemandRepository extends JpaRepository<DemandModel, Integer> {
    @Query(value = "SELECT * FROM Demands WHERE location=:city",nativeQuery = true)
    public List<DemandModel> findByCity(@Param("city")String city);

    @Query(value = "SELECT * FROM Demands WHERE level=:level", nativeQuery = true)
    public List<DemandModel> findByLevel(@Param("level")String level);

    @Query(value = "SELECT * FROM Demands WHERE status=:status",nativeQuery = true)
    public List<DemandModel> findByStatus(@Param("status")String status);

    @Query(value = "SELECT * FROM DEMANDS",nativeQuery = true)
    public List<DemandModel> findBySkills();

    @Query(value = "SELECT * FROM Demands WHERE project_name=:prj OR mgr_name=:mgr",nativeQuery = true)
    public List<DemandModel> findByName(@Param("prj")String prj, @Param("mgr")String mgr);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Demands SET status = 'closed' WHERE id = :id",nativeQuery = true)
    public void updateDemandStatus(@Param("id")Integer eid);
}
