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

    public void createDemands(List<DemandModel> demands){
        demandRepository.saveAll(demands);
    }

    public List<DemandModel> getDemands(){
        return demandRepository.findAll();
    }

    public List<DemandModel> findByCity(String city){
        return demandRepository.findByCity(city);
    }

    public List<DemandModel> findByLevel(String level){
        return demandRepository.findByLevel(level);
    }

    public List<DemandModel> findByStatus(String status){
        return demandRepository.findByStatus(status);
    }

    public List<DemandModel> findByName(String proj, String mgr){
        return demandRepository.findByName(proj,mgr);
    }

    public List<DemandModel> findBySkills(List<String> skills){
        List<DemandModel> temp = demandRepository.findBySkills();
        for(int i = 0;i< temp.size();i++){
            if(!temp.get(i).getSkills().containsAll(skills)){
                temp.remove(i);
                i -= 1;
            }
        }
        return temp;
    }
}
