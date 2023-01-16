package org.example.isdb4c.services;

import org.example.isdb4c.model.Incident;
import org.example.isdb4c.model.network.IncidentNetTransfer;
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

    public List<Incident> getAllIncidentsExcept(List<Integer> ids, Integer accessLvl) {
        return this.incidentRepository.findAllByIdNotInAndAccessLvlLessThanEqual(ids, accessLvl);
    }


    public void addIncident(IncidentNetTransfer incident) {
        Incident i = new Incident();
        i.setTime(incident.getTime());
        i.setPlace(incident.getPlace());
        i.setType(incident.getType());
        i.setDescription(incident.getDescription());
        i.setAccessLvl(incident.getAccessLvl());
        this.incidentRepository.save(i);
    }

    public void updateIncident(IncidentNetTransfer updIncident, Integer id) {
        this.incidentRepository.updateIncidentById(
                updIncident.getTime(),
                updIncident.getPlace(),
                updIncident.getType(),
                updIncident.getDescription(),
                updIncident.getAccessLvl(),
                id
        );
    }
}
