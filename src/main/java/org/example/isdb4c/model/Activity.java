package org.example.isdb4c.model;

import lombok.Data;
import org.example.isdb4c.model.types.Legality;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "activity", schema = "public")
public class Activity {

    @Id
    @SequenceGenerator(name = "activitySeq", sequenceName = "activity_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activitySeq")
    private Integer id;
    @Column(name = "activity_type")
    private String activityType;
    @Enumerated(EnumType.STRING)
    @Column(name = "cathegory")
    private Legality legality;
    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "activities")
    List<ObservedPerson> people;
    @ManyToMany(mappedBy = "activities")
    List<Organization> organizations;
}
