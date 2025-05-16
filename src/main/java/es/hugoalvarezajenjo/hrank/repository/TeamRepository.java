package es.hugoalvarezajenjo.hrank.repository;

import es.hugoalvarezajenjo.hrank.repository.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
}
