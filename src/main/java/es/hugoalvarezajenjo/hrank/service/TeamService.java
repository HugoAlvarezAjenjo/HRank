package es.hugoalvarezajenjo.hrank.service;

import es.hugoalvarezajenjo.hrank.domain.Team;

import java.util.List;

public interface TeamService {

    List<Team> findAll();

    Team findByAccessKey(String accessKey);

    void save(Team team);

    void deleteById(Long id);
}
