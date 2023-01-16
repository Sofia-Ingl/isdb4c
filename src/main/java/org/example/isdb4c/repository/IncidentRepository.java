package org.example.isdb4c.repository;

import org.example.isdb4c.model.Evidence;
import org.example.isdb4c.model.Incident;
import org.example.isdb4c.model.types.CaseCompleteness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Integer> {

    List<Incident> findAllByAccessLvlLessThanEqual(Integer accessLvl);
    List<Incident> findAllByCases_IdAndAccessLvlLessThanEqual(Integer caseId, Integer accessLvl);
    List<Incident> findAllByIdNotInAndAccessLvlLessThanEqual(List<Integer> ids, Integer accessLvl);
    @Modifying
    @Transactional
    @Query(value = "update incident set " +
            "event_time = :eventTime, " +
            "place = :place, " +
            "description = :description, " +
            "event_type = :type, " +
            "access_lvl = :accessLvl " +
            "where id = :incidentId", nativeQuery = true)
    void updateIncidentById(@Param("eventTime") LocalDateTime eventTime,
                            @Param("place") String place,
                        @Param("type") String type,
                        @Param("description") String description,
                            @Param("accessLvl") Integer accessLvl,
                        @Param("incidentId") Integer incidentId);

}
