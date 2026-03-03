package es.hugoalvarezajenjo.hrank.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionConfig {
    private Long id;
    private String title;
    private LocalDateTime endTime;
}
