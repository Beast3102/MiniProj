package com.assessment.MiniProject.controller;

import com.assessment.MiniProject.model.DemandModel;
import com.assessment.MiniProject.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Declares the class as Controller Class
@RequestMapping("/demand")
public class DemandController {
    @Autowired
    DemandService demandService;

    @PostMapping("/addDemand")
    public String createDemand(@RequestBody  DemandModel demandModel){
        demandService.createDemand(demandModel);
        return "Demand created";
    }

    @GetMapping("/getAll")
    public List<DemandModel> getAll() {
        return demandService.getDemands();
    }
}
