package org.example.isdb4c.controllers;

import org.example.isdb4c.model.ObservedPerson;
import org.example.isdb4c.model.network.*;
import org.example.isdb4c.security.jwt.JwtProvider;
import org.example.isdb4c.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;
    private final JwtProvider jwtProvider;
    private final CaseService caseService;
    private final MembershipService membershipService;
    private final ActivityService activityService;

    public PersonController(@Autowired PersonService personService,
                            @Autowired JwtProvider jwtProvider,
                            @Autowired CaseService caseService,
                            @Autowired MembershipService membershipService,
                            @Autowired ActivityService activityService) {

        this.personService = personService;
        this.jwtProvider = jwtProvider;
        this.caseService = caseService;
        this.membershipService = membershipService;
        this.activityService = activityService;
    }

    @GetMapping("/all")
    public List<PersonNetTransfer> getAll(@RequestHeader("Authorization") String authHeader) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        return personService
                .getAllObservedPeople(accessLvl)
                .stream()
                .map(PersonNetTransfer::new)
                .collect(Collectors.toList());
    }


    @PostMapping("/add")
    public void addPerson(@RequestBody PersonNetTransfer newPerson) {
        this.personService.addPerson(newPerson);
    }


    @PostMapping("/{id}/modify")
    public void modifyPersonFields(@PathVariable @NotNull Integer id, @RequestBody PersonNetTransfer updPerson) {
        this.personService.updatePerson(updPerson, id);
    }

    @GetMapping("/{id}/cases")
    public List<CaseNetTransfer> getPersonCases(@RequestHeader("Authorization") String authHeader,
                                                @PathVariable @NotNull Integer id) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        return caseService
                .getAllPersonCases(id,accessLvl)
                .stream()
                .map(CaseNetTransfer::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/witness_cases")
    public List<CaseNetTransfer> getPersonWitnessCases(@RequestHeader("Authorization") String authHeader,
                                                @PathVariable @NotNull Integer id) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        return caseService
                .getAllPersonWitnessCases(id,accessLvl)
                .stream()
                .map(CaseNetTransfer::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/memberships")
    public List<MembershipNetTransfer> getPersonMemberships(@RequestHeader("Authorization") String authHeader,
                                                            @PathVariable @NotNull Integer id) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        return membershipService
                .getAllPersonMemberships(id, accessLvl)
                .stream()
                .map(MembershipNetTransfer::new)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}/memberships/add")
    public void addPersonMemberships(@PathVariable @NotNull Integer id,
                                     @RequestBody List<MembershipNetTransfer> newMemberships) {
        this.membershipService.insertMemberships(newMemberships);
    }

    @GetMapping("/{id}/memberships/delete")
    public void deletePersonMemberships(@PathVariable @NotNull Integer id,
                                     @RequestBody List<Integer> orgIds) {
        this.membershipService.deletePersonMemberships(orgIds, id);
    }

    @GetMapping("/{id}/activities")
    public List<ActivityNetTransfer> getPersonActivities(@RequestHeader("Authorization") String authHeader,
                                                         @PathVariable @NotNull Integer id) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        return activityService
                .getAllPersonActivities(id)
                .stream()
                .map(ActivityNetTransfer::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/activities/add")
    public void addPersonActivities(@PathVariable @NotNull Integer id,
                                     @RequestBody List<Integer> activityIds) {
        this.activityService.insertPersonActivities(activityIds, id);
    }

    @GetMapping("/{id}/activities/delete")
    public void deletePersonActivities(@PathVariable @NotNull Integer id,
                                       @RequestBody List<Integer> activityIds) {
        this.activityService.deletePersonActivities(activityIds, id);
    }

}
