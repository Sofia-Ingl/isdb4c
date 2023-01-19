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

    public Article getArticleById(Integer id) {
        return this.articleRepository.findById(id).get();
    }

    public List<Article> getAllCaseArticles(Integer caseId) {
        return this.articleRepository.findAllByCases_Id(caseId);
    }

    public List<Article> getAllArticlesExcept(List<Integer> ids) {
        return articleRepository.findAllByIdNotIn(ids);
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}
