package org.example.isdb4c.controllers;

import org.example.isdb4c.model.network.CaseNetTransfer;
import org.example.isdb4c.model.network.MembershipNetTransfer;
import org.example.isdb4c.model.network.OrganizationNetTransfer;
import org.example.isdb4c.security.jwt.JwtProvider;
import org.example.isdb4c.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    public OrganizationController(
                          @Autowired JwtProvider jwtProvider,
                          @Autowired CaseService caseService,
                          @Autowired OrganizationService organizationService,
                          @Autowired MembershipService membershipService
    ) {

        this.jwtProvider = jwtProvider;
        this.caseService = caseService;
        this.organizationService = organizationService;
        this.membershipService = membershipService;
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

    @PostMapping("/add")
    public void addOrg(@RequestBody OrganizationNetTransfer newOrg) {
        this.organizationService.addOrganization(newOrg);
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
}
