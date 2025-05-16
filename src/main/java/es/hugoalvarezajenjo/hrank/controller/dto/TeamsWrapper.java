package es.hugoalvarezajenjo.hrank.controller.dto;

import es.hugoalvarezajenjo.hrank.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamsWrapper {
    private List<Team> teams;
}
