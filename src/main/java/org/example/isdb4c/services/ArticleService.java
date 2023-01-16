package org.example.isdb4c.services;

import org.example.isdb4c.model.Article;
import org.example.isdb4c.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(@Autowired ArticleRepository repository) {
        this.articleRepository = repository;
    }

    public List<Article> getAllCaseArticles(Integer caseId) {
        return this.articleRepository.findAllByCases_Id(caseId);
    }

    public List<Article> getAllArticlesExcept(List<Integer> ids) {
        return articleRepository.findAllByIdNotIn(ids);
    }
}
