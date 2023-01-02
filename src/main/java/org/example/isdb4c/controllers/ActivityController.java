package org.example.isdb4c.controllers;

import org.example.isdb4c.model.network.ActivityNetTransfer;
import org.example.isdb4c.model.network.CaseNetTransfer;
import org.example.isdb4c.security.jwt.JwtProvider;
import org.example.isdb4c.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(@Autowired ActivityService activityService) {

        this.activityService = activityService;
    }

    @GetMapping("/all")
    public List<ActivityNetTransfer> getAll() {
        return activityService
                .getAllActivities()
                .stream()
                .map(ActivityNetTransfer::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public void addActivity(@RequestBody ActivityNetTransfer newActivity) {
        this.activityService.addActivity(newActivity);
    }

}
