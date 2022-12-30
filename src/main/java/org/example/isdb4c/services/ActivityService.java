package org.example.isdb4c.services;

import org.example.isdb4c.model.Activity;
import org.example.isdb4c.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(@Autowired ActivityRepository repository) {
        this.activityRepository = repository;
    }

    public List<Activity> getAllPersonActivities(Integer personId) {
        return this.activityRepository.findAllByPeople_Id(personId);
    }

    public List<Activity> getAllOrganizationActivities(Integer orgId) {
        return this.activityRepository.findAllByOrganizations_Id(orgId);
    }
}
