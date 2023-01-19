package org.example.isdb4c.controllers;

import org.example.isdb4c.model.Article;
import org.example.isdb4c.model.Incident;
import org.example.isdb4c.model.network.ArticleNetTransfer;
import org.example.isdb4c.model.network.IncidentNetTransfer;
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
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleService articleService;
    private final JwtProvider jwtProvider;

    public ArticleController(@Autowired ArticleService articleService,
                             @Autowired JwtProvider jwtProvider) {

        this.articleService = articleService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/all")
    public List<ArticleNetTransfer> getAll() {

        return articleService
                .getAllArticles()
                .stream()
                .map(ArticleNetTransfer::new)
                .collect(Collectors.toList());
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

    @GetMapping("/{id}")
    public ResponseEntity<ArticleNetTransfer> getArticleById(@RequestHeader("Authorization") String authHeader,
                                                               @PathVariable @NotNull Integer id) {
        Integer accessLvl = jwtProvider.getAccessLvlFromToken(jwtProvider.getTokenFromHeader(authHeader));
        try {
            Article i = this.articleService.getArticleById(id);
            return new ResponseEntity<>(new ArticleNetTransfer(i), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
