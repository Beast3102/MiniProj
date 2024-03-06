package com.assessment.MiniProject.controller;

import com.assessment.MiniProject.model.DemandModel;
import com.assessment.MiniProject.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // Declares the class as Controller Class
@RequestMapping("/demands")
public class DemandController {
    @Autowired
    DemandService demandService;

    @PostMapping("/addDemand")
    public String createDemand(@RequestBody  DemandModel demandModel){
        demandService.createDemand(demandModel);
        return "Demand created";
    }

    @PostMapping("/addDemands")
    public String addDemands(@RequestBody List<DemandModel> demandModel){
        demandService.createDemands(demandModel);
        return "Demands added";
    }

    @GetMapping("/getDemands")
    public List<DemandModel> getAll() {
        return demandService.getDemands();
    }

    @GetMapping("/getCustomDemand/city")
    public List<DemandModel> getByCity(@RequestParam String location){return demandService.findByCity(location);}

    @GetMapping("/getCustomDemand/status")
    public List<DemandModel> getByStatus(@RequestParam String status){return demandService.findByStatus(status);}

    @GetMapping("/getCustomDemand/level")
    public List<DemandModel> getByLevel(@RequestParam String level){return demandService.findByLevel(level);}

    @GetMapping("/getCustomDemand/skills")
    public List<DemandModel> getbySkills(@RequestBody String skills){
        skills = skills.replaceAll("\"","").replaceAll("\n","")
                .replaceAll("\r","");
        String[] skill = skills.split(",");
        List<String> val = new ArrayList<>();
        val.add(skill[0]);
        val.add(skill[1]);
        return demandService.findBySkills(val);
    }

    @GetMapping("/getCustomDemand/name")
    public List<DemandModel> getByName(@RequestParam(required = false) String proj_name,
                                       @RequestParam(required = false) String mgr_name){
        return demandService.findByName(proj_name,mgr_name);
    }

}
