package org.example.isdb4c.controllers;

import org.example.isdb4c.model.network.ArticleNetTransfer;
import org.example.isdb4c.model.network.IncidentNetTransfer;
import org.example.isdb4c.security.jwt.JwtProvider;
import org.example.isdb4c.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleService articleService;
    private final JwtProvider jwtProvider;

    public ArticleController(@Autowired ArticleService articleService,
                             @Autowired JwtProvider jwtProvider) {

        this.articleService = articleService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/all_except")
    public List<ArticleNetTransfer> getAllExcept(@RequestBody List<ArticleNetTransfer> notIncluded) {

        List<Integer> ids = notIncluded.stream().map(ArticleNetTransfer::getId).collect(Collectors.toList());
        ids.add(-1);
        return articleService
                .getAllArticlesExcept(ids)
                .stream()
                .map(ArticleNetTransfer::new)
                .collect(Collectors.toList());
    }
}
