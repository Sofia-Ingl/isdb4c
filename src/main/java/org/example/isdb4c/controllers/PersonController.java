package org.example.isdb4c.controllers;

import org.example.isdb4c.model.ObservedPerson;
import org.example.isdb4c.security.jwt.JwtProvider;
import org.example.isdb4c.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;
    private final JwtProvider jwtProvider;

    public PersonController(@Autowired PersonService personService,
                            @Autowired JwtProvider jwtProvider) {

        this.personService = personService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/all")
    public List<ObservedPerson> getAll(@RequestHeader("Authorization") String authHeader) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        List<ObservedPerson> res = personService.getAllObservedPeople(accessLvl);
        for (ObservedPerson p:
             res) {
            p.setCases(null);
            p.setActivities(null);
            p.setMemberships(null);
            p.setWitnessCases(null);
        }
        return res;
    }

}
