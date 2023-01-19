package org.example.isdb4c.controllers;

import org.example.isdb4c.model.Incident;
import org.example.isdb4c.model.Organization;
import org.example.isdb4c.model.network.*;
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
@RequestMapping("/api/organization")
public class OrganizationController {

    private final JwtProvider jwtProvider;
    private final CaseService caseService;
    private final OrganizationService organizationService;
    private final MembershipService membershipService;
    private final ActivityService activityService;

    public OrganizationController(
                          @Autowired JwtProvider jwtProvider,
                          @Autowired CaseService caseService,
                          @Autowired OrganizationService organizationService,
                          @Autowired MembershipService membershipService,
                          @Autowired ActivityService activityService
    ) {

        this.jwtProvider = jwtProvider;
        this.caseService = caseService;
        this.organizationService = organizationService;
        this.membershipService = membershipService;
        this.activityService = activityService;
    }

    @GetMapping("/all")
    public List<OrganizationNetTransfer> getAll(@RequestHeader("Authorization") String authHeader) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        return organizationService
                .getAllOrganizations(accessLvl)
                .stream()
                .map(OrganizationNetTransfer::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/all_except")
    public List<OrganizationNetTransfer> getAllExcept(@RequestHeader("Authorization") String authHeader, @RequestBody List<OrganizationNetTransfer> notIncluded) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));

        List<Integer> ids = notIncluded.stream().map(OrganizationNetTransfer::getId).collect(Collectors.toList());
        ids.add(-1);
        return organizationService
                .getAllOrganizationsExcept(ids, accessLvl)
                .stream()
                .map(OrganizationNetTransfer::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public void addOrg(@RequestBody OrganizationNetTransfer newOrg) {
        this.organizationService.addOrganization(newOrg);
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrganizationNetTransfer> getOrganizationById(@RequestHeader("Authorization") String authHeader,
                                                             @PathVariable @NotNull Integer id) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        try {
            Organization o = this.organizationService.getOrganizationById(id);
            if (o.getAccessLvl() > accessLvl) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(new OrganizationNetTransfer(o), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/modify")
    public OrganizationNetTransfer modifyOrganizationFields(@PathVariable @NotNull Integer id,
                                 @RequestBody OrganizationNetTransfer updOrg) {
        organizationService.updateOrganization(updOrg, id);
        return updOrg;
    }

    @GetMapping("/{id}/memberships")
    public List<MembershipNetTransfer> getOrganizationMemberships(@RequestHeader("Authorization") String authHeader,
                                                            @PathVariable @NotNull Integer id) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        return membershipService
                .getAllOrganizationMemberships(id, accessLvl)
                .stream()
                .map(MembershipNetTransfer::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/{id}/memberships/add_new")
    public void addOrganizationMemberships(@PathVariable @NotNull Integer id,
                                     @RequestBody List<MembershipNetTransfer> newMemberships) {
        this.membershipService.insertMemberships(newMemberships);
    }

    @PostMapping("/{id}/memberships/delete")
    public void deleteOrganizationMemberships(@PathVariable @NotNull Integer id,
                                        @RequestBody List<Integer> personIds) {
        this.membershipService.deleteOrganizationMemberships(personIds, id);
    }

    @GetMapping("/{id}/cases")
    public List<CaseNetTransfer> getOrganizationCases(@RequestHeader("Authorization") String authHeader,
                                                @PathVariable @NotNull Integer id) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        return caseService
                .getAllOrganizationCases(id,accessLvl)
                .stream()
                .map(CaseNetTransfer::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/activities")
    public List<ActivityNetTransfer> getOrganizationActivities(@RequestHeader("Authorization") String authHeader,
                                                         @PathVariable @NotNull Integer id) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        return activityService
                .getAllOrganizationActivities(id)
                .stream()
                .map(ActivityNetTransfer::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/{id}/activities/add")
    public void addPersonActivities(@PathVariable @NotNull Integer id,
                                    @RequestBody List<Integer> activityIds) {
        this.activityService.insertOrganizationActivities(activityIds, id);
    }

    @PostMapping("/{id}/activities/delete")
    public void deletePersonActivities(@PathVariable @NotNull Integer id,
                                       @RequestBody List<Integer> activityIds) {
        this.activityService.deleteOrganizationActivities(activityIds, id);
    }
}
