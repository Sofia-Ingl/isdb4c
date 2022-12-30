package org.example.isdb4c.controllers;

import org.example.isdb4c.model.ObservedPerson;
import org.example.isdb4c.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(@Autowired PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/all")
    public List<ObservedPerson> getAll() {

        List<ObservedPerson> res = personService.getAllObservedPeople(2);
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
