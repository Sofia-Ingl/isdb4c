package org.example.isdb4c.repository;

import org.example.isdb4c.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

    //Optional<Position> findById(Integer id);
}
