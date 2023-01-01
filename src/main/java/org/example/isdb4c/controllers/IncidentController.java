package org.example.isdb4c.controllers;


import org.example.isdb4c.model.network.IncidentNetTransfer;
import org.example.isdb4c.model.network.OrganizationNetTransfer;
import org.example.isdb4c.security.jwt.JwtProvider;
import org.example.isdb4c.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/incident")
public class IncidentController {

    private final PersonService personService;
    private final JwtProvider jwtProvider;
    private final CaseService caseService;
    private final OrganizationService organizationService;
    private final IncidentService incidentService;
    private final EvidenceService evidenceService;
    private final EmployeeService employeeService;
    private final ArticleService articleService;

    public IncidentController(@Autowired PersonService personService,
                          @Autowired JwtProvider jwtProvider,
                          @Autowired CaseService caseService,
                          @Autowired OrganizationService organizationService,
                          @Autowired EvidenceService evidenceService,
                          @Autowired IncidentService incidentService,
                          @Autowired EmployeeService employeeService,
                          @Autowired ArticleService articleService) {

        this.personService = personService;
        this.jwtProvider = jwtProvider;
        this.caseService = caseService;
        this.incidentService = incidentService;
        this.evidenceService = evidenceService;
        this.organizationService = organizationService;
        this.employeeService = employeeService;
        this.articleService = articleService;
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

}
