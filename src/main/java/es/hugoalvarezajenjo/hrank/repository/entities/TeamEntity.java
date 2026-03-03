package es.hugoalvarezajenjo.hrank.repository.entities;

import es.hugoalvarezajenjo.hrank.domain.ProgressPhase;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String accessKey;

    private int percentage;
    @Enumerated(EnumType.STRING)
    private ProgressPhase phase;
}
