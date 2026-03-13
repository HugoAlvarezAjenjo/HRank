package es.hugoalvarezajenjo.hrank.controller.dto;

import java.util.ArrayList;
import java.util.List;

import es.hugoalvarezajenjo.hrank.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamsWrapper {
    private List<Team> teams = new ArrayList<>();
}
