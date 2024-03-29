package org.example.isdb4c.controllers;


import org.example.isdb4c.model.Case;
import org.example.isdb4c.model.Incident;
import org.example.isdb4c.model.network.CaseNetTransfer;
import org.example.isdb4c.model.network.IncidentNetTransfer;
import org.example.isdb4c.model.network.OrganizationNetTransfer;
import org.example.isdb4c.security.jwt.JwtProvider;
import org.example.isdb4c.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/incident")
public class IncidentController {

    private final JwtProvider jwtProvider;
    private final CaseService caseService;
    private final IncidentService incidentService;

    public IncidentController(@Autowired JwtProvider jwtProvider,
                          @Autowired CaseService caseService,
                          @Autowired IncidentService incidentService) {

        this.jwtProvider = jwtProvider;
        this.caseService = caseService;
        this.incidentService = incidentService;
    }

    @GetMapping("/all")
    public List<IncidentNetTransfer> getAll(@RequestHeader("Authorization") String authHeader) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        return incidentService
                .getAllIncidents(accessLvl)
                .stream()
                .map(IncidentNetTransfer::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/all_except")
    public List<IncidentNetTransfer> getAllExcept(@RequestHeader("Authorization") String authHeader, @RequestBody List<IncidentNetTransfer> notIncluded) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));

        List<Integer> ids = notIncluded.stream().map(IncidentNetTransfer::getId).collect(Collectors.toList());
        ids.add(-1);
        return incidentService
                .getAllIncidentsExcept(ids, accessLvl)
                .stream()
                .map(IncidentNetTransfer::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public void addOrg(@RequestBody IncidentNetTransfer newIncident) {
        this.incidentService.addIncident(newIncident);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidentNetTransfer> getIncidentById(@RequestHeader("Authorization") String authHeader,
                                             @PathVariable @NotNull Integer id) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        try {
            Incident i = this.incidentService.getIncidentById(id);
            if (i.getAccessLvl() > accessLvl) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(new IncidentNetTransfer(i), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/modify")
    public IncidentNetTransfer modifyIncidentFields(@PathVariable @NotNull Integer id,
                                 @RequestBody IncidentNetTransfer updIncident) {
        incidentService.updateIncident(updIncident, id);
        return updIncident;
    }

    @GetMapping("/{id}/cases")
    public List<CaseNetTransfer> getIncidentCases(@RequestHeader("Authorization") String authHeader,
                                                            @PathVariable @NotNull Integer id) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        return caseService
                .getAllIncidentCases(id, accessLvl)
                .stream()
                .map(CaseNetTransfer::new)
                .collect(Collectors.toList());
    }

}
