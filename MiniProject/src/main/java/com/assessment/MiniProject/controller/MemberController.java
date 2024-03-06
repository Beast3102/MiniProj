package com.assessment.MiniProject.controller;

import com.assessment.MiniProject.model.Membermodel;
import com.assessment.MiniProject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/addMember")
    public String addMember(@RequestBody Membermodel membermodel){
        memberService.createMember(membermodel);
        return "Member added successfully";
    }

    @GetMapping("/getMembers")
    public List<Membermodel> getMembers(){return memberService.getMembers();}

    @GetMapping("/getMembersForDemand")
    public List<Membermodel> getMemberForDemand(@RequestParam int id){
        return memberService.getMemberForDemand(id);
    }
}
