package org.example.isdb4c.controllers;

import org.example.isdb4c.model.network.*;
import org.example.isdb4c.security.jwt.JwtProvider;
import org.example.isdb4c.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/case")
public class CaseController {

    private final PersonService personService;
    private final JwtProvider jwtProvider;
    private final CaseService caseService;
    private final OrganizationService organizationService;
    private final IncidentService incidentService;
    private final EvidenceService evidenceService;
    private final EmployeeService employeeService;
    private final ArticleService articleService;

    public CaseController(@Autowired PersonService personService,
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
    public List<CaseNetTransfer> getAll(@RequestHeader("Authorization") String authHeader) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        return caseService
                .getAllCases(accessLvl)
                .stream()
                .map(CaseNetTransfer::new)
                .collect(Collectors.toList());
    }







    @PostMapping("/{id}/modify")
    public void modifyCaseFields(@PathVariable @NotNull Integer id, @RequestBody CaseNetTransfer updCase) {
        caseService.updateCase(updCase, id);
    }







    @GetMapping("/{id}/people_participants")
    public List<PersonNetTransfer> getCaseParticipants(@RequestHeader("Authorization") String authHeader,
                                                @PathVariable @NotNull Integer id) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        return personService
                .getAllCaseParticipants(id,accessLvl)
                .stream()
                .map(PersonNetTransfer::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/people_witnesses")
    public List<PersonNetTransfer> getCaseWitnesses(@RequestHeader("Authorization") String authHeader,
                                                       @PathVariable @NotNull Integer id) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        return personService
                .getAllCaseWitnesses(id,accessLvl)
                .stream()
                .map(PersonNetTransfer::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/orgs_participants")
    public List<OrganizationNetTransfer> getCaseOrgParticipants(@RequestHeader("Authorization") String authHeader,
                                                                @PathVariable @NotNull Integer id) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        return organizationService
                .getAllCaseParticipantOrgs(id,accessLvl)
                .stream()
                .map(OrganizationNetTransfer::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/articles")
    public List<ArticleNetTransfer> getCaseArticles(@PathVariable @NotNull Integer id) {
        return articleService
                .getAllCaseArticles(id)
                .stream()
                .map(ArticleNetTransfer::new)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}/incidents")
    public List<IncidentNetTransfer> getCaseIncidents(@RequestHeader("Authorization") String authHeader,
                                                      @PathVariable @NotNull Integer id) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        return incidentService
                .getAllCaseIncidents(id,accessLvl)
                .stream()
                .map(IncidentNetTransfer::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/evidences")
    public List<EvidenceNetTransfer> getCaseEvidences(@RequestHeader("Authorization") String authHeader,
                                                          @PathVariable @NotNull Integer id) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        return evidenceService
                .getAllCaseEvidences(id,accessLvl)
                .stream()
                .map(EvidenceNetTransfer::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/responsible_employees")
    public List<EmployeeNetTransfer> getCaseResponsibleEmployees(@PathVariable @NotNull Integer id) {
        return employeeService
                .getAllResponsibleEmployees(id)
                .stream()
                .map(EmployeeNetTransfer::new)
                .collect(Collectors.toList());
    }
}
