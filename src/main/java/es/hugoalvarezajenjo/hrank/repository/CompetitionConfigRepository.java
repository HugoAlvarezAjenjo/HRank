package es.hugoalvarezajenjo.hrank.repository;

import es.hugoalvarezajenjo.hrank.repository.entities.CompetitionConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionConfigRepository extends JpaRepository<CompetitionConfigEntity, Long> {
}
