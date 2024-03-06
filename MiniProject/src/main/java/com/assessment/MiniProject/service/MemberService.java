package com.assessment.MiniProject.service;

import com.assessment.MiniProject.model.DemandModel;
import com.assessment.MiniProject.model.Membermodel;
import com.assessment.MiniProject.repository.DemandRepository;
import com.assessment.MiniProject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        }
        eids.add(memberRepository.getMemberEid(dm.getLocation(),"available",dm.getLevel()));

        for(int i = 1; i < eids.size();i++){
            eids.get(0).retainAll(eids.get(i));
        }

        if(dm.getStatus() == null){
            return memberRepository.findAll();
        }
        return memberRepository.findAllById(eids.get(0)).stream()
                .sorted(Comparator.comparing(Membermodel::getDoj)
                        .thenComparing(Comparator.comparing(Membermodel::getFirstname))
                        .thenComparing(Comparator.comparing(Membermodel::getLocation)))
                .collect(Collectors.toList());
    }

    public List<Membermodel> getMemberFromDemandRequest(String name,String location,String level,String status,int eid,List<String> skills){
        List<Integer> temp = memberRepository.getMembersEid();
        if(name != null)
            temp.retainAll(memberRepository.getMemberForName(name));
        if(location != null)
            temp.retainAll(memberRepository.getMemberForLocation(location));
        if(level != null)
            temp.retainAll(memberRepository.getMemberForLevel(level));
        if(status != null)
            temp.retainAll(memberRepository.getMemberForStatus(status));
        if(eid != 0)
            temp.retainAll(memberRepository.getMemberForEid(eid));
        if(skills != null) {
            for (int i = 0; i < skills.size(); i++) {
                temp.retainAll(memberRepository.getMemberWithSkill(skills.get(i)));
            }
        }

        return memberRepository.findAllById(temp).stream()
                .sorted(Comparator.comparing(Membermodel::getDoj)
                        .thenComparing(Comparator.comparing(Membermodel::getFirstname))
                        .thenComparing(Comparator.comparing(Membermodel::getLocation)))
                .collect(Collectors.toList());
    }
}
