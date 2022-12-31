package org.example.isdb4c.services;

import org.example.isdb4c.model.Incident;
import org.example.isdb4c.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentService(@Autowired IncidentRepository repository) {
        this.incidentRepository = repository;
    }

    public List<Incident> getAllIncidents(Integer accessLvl) {
        return this.incidentRepository.findAllByAccessLvlLessThanEqual(accessLvl);
    }

    public List<Incident> getAllCaseIncidents( Integer caseId, Integer accessLvl) {
        return this.incidentRepository.findAllByCases_IdAndAccessLvlLessThanEqual(caseId, accessLvl);
    }
}
