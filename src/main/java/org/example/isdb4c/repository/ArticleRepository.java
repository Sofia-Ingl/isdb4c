package org.example.isdb4c.repository;

import org.example.isdb4c.model.Activity;
import org.example.isdb4c.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Override
    List<Article> findAll();

    List<Article> findAllByIdNotIn(List<Integer> integers);

    List<Article> findAllByCases_Id(Integer caseId);


}
