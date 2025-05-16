package es.hugoalvarezajenjo.hrank.service;

import es.hugoalvarezajenjo.hrank.domain.Team;
import es.hugoalvarezajenjo.hrank.repository.TeamRepository;
import es.hugoalvarezajenjo.hrank.service.mapper.TeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService{
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll()
                .stream()
                .map(teamMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void save(final Team team) {
        this.teamRepository.save(
                this.teamMapper.toEntity(team)
        );
    }
}
