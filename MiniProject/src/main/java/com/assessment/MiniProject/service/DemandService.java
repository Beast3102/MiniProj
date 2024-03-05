package com.assessment.MiniProject.service;

import com.assessment.MiniProject.model.DemandModel;
import com.assessment.MiniProject.repository.DemandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandService {
    @Autowired
    DemandRepository demandRepository;

    public void createDemand(DemandModel demandModel) {
        demandRepository.save(demandModel); // save() method (CRUD operation) lies in JPA repository
    }

    public List<DemandModel> getDemands(){
        return demandRepository.findAll();
    }
}
