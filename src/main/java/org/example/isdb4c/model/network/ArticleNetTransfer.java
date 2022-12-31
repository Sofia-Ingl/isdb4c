package org.example.isdb4c.model.network;

import lombok.Data;
import org.example.isdb4c.model.Article;
import java.io.Serializable;

@Data
public class ArticleNetTransfer implements Serializable {

    private Integer id;
    private String departmentName;
    private String code;
    private Integer articleNumber;
    private String description;

    public ArticleNetTransfer() {}

    public ArticleNetTransfer(Article article) {
        id = article.getId();
        departmentName = article.getDepartment().getName();
        code = article.getCode();
        articleNumber = article.getArticleNumber();
        description = article.getDescription();
    }
}
