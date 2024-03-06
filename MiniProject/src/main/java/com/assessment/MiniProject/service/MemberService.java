package com.assessment.MiniProject.service;

import com.assessment.MiniProject.model.DemandModel;
import com.assessment.MiniProject.model.MappedModel;
import com.assessment.MiniProject.model.Membermodel;
import com.assessment.MiniProject.repository.DemandRepository;
import com.assessment.MiniProject.repository.MappedRepository;
import com.assessment.MiniProject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    DemandRepository demandRepository;

    @Autowired
    MappedRepository mappedRepository;

    public void createMember(Membermodel membermodel){
        memberRepository.save(membermodel);
    }

    public List<Membermodel> getMembers(){return memberRepository.findAll();}

    public List<Integer> getMemberForDemand(int id){
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

        return eids.get(0);
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

    public String completeRequest(int id){
        if(demandRepository.findById(id).isPresent()) {
            if (Objects.equals(demandRepository.findById(id).get().getStatus(), "open")) {
                List<Integer> eid = getMemberForDemand(id);
                if (!eid.isEmpty()) {
                    Random random = new Random();
                    int selection = random.nextInt(eid.size());
                    int mapped_eid = eid.get(selection);
                    MappedModel mp = new MappedModel();
                    mp.setEid(mapped_eid);
                    mp.setProject_id(id);
                    mappedRepository.save(mp);
                    memberRepository.updateMemberStatus(mapped_eid);
                    demandRepository.updateDemandStatus(id);
                    return ("Member " + mapped_eid + " assigned to project " + id);
                } else {
                    return "No available members";
                }
            }
            else {
                return "Request is closed";
            }
        }
        else {
            return "No such request";
        }
    }
}
