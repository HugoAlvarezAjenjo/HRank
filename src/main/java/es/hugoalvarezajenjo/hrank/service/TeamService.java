package es.hugoalvarezajenjo.hrank.service;

import es.hugoalvarezajenjo.hrank.domain.Team;

import java.util.List;

public interface TeamService {

    List<Team> findAll();

    void save(Team team);
}
