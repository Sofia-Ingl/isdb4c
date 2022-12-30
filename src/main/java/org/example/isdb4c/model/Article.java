package org.example.isdb4c.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "article", schema = "public")
public class Article {

    @Id
    @SequenceGenerator(name = "articleSeq", sequenceName = "article_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "articleSeq")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Department department;

    @Column(name = "code")
    private String code;
    @Column(name = "article_number")
    private Integer articleNumber;
    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "articles")
    List<Case> cases;

}
