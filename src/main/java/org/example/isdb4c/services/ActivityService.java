package org.example.isdb4c.services;

import org.example.isdb4c.model.Activity;
import org.example.isdb4c.model.network.ActivityNetTransfer;
import org.example.isdb4c.model.types.Legality;
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

    public List<Activity> getAllActivities() {
        return this.activityRepository.findAll();
    }

    public List<Activity> getAllPersonActivities(Integer personId) {
        return this.activityRepository.findAllByPeople_Id(personId);
    }

    public List<Activity> getAllOrganizationActivities(Integer orgId) {
        return this.activityRepository.findAllByOrganizations_Id(orgId);
    }

    public void insertPersonActivities(List<Integer> activityIds, Integer personId) {
        for (Integer activityId:
                activityIds) {
            this.activityRepository.insertPersonActivity(activityId, personId);
        }
    }

    public void deletePersonActivities(List<Integer> activityIds, Integer personId) {
        this.activityRepository.deletePersonActivities(personId, activityIds);
    }


    public void insertOrganizationActivities(List<Integer> activityIds, Integer orgId) {
        for (Integer activityId:
                activityIds) {
            this.activityRepository.insertOrganizationActivity(activityId, orgId);
        }
    }

    public void deleteOrganizationActivities(List<Integer> activityIds, Integer orgId) {
        this.activityRepository.deleteOrganizationActivities(orgId, activityIds);
    }

    public void addActivity(ActivityNetTransfer newActivity) {
        Activity a = new Activity();
        a.setActivityType(newActivity.getActivityType());
        a.setLegality(Legality.valueOfDescription(newActivity.getLegality()));
        a.setDescription(newActivity.getDescription());
        this.activityRepository.save(a);
    }
}
