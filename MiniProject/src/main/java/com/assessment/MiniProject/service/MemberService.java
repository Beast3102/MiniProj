package com.assessment.MiniProject.service;

import com.assessment.MiniProject.model.DemandModel;
import com.assessment.MiniProject.model.Membermodel;
import com.assessment.MiniProject.queryClass;
import com.assessment.MiniProject.repository.DemandRepository;
import com.assessment.MiniProject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    DemandRepository demandRepository;

    public void createMember(Membermodel membermodel){
        memberRepository.save(membermodel);
    }

    public List<Membermodel> getMembers(){return memberRepository.findAll();}

    public List<Membermodel> getMemberForDemand(int id){
        Optional<DemandModel> temp = demandRepository.findById(id);
        DemandModel dm = null;
        if(temp.isPresent()){
            dm = temp.get();
        }
        List<List<Integer>> eids = new ArrayList<>();
        for(int i = 0; i < dm.getSkills().size();i++){
            eids.add(memberRepository.getMemberWithSkill(dm.getSkills().get(i)));
            System.out.println(dm.getSkills().get(i));
        }
        eids.add(memberRepository.getMemberEid(dm.getLocation(),"available",dm.getLevel()));

        for(int i = 1; i < eids.size();i++){
            eids.get(0).retainAll(eids.get(i));
        }
        return memberRepository.findAllById(eids.get(0));
    }
}
