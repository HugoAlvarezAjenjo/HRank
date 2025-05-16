package es.hugoalvarezajenjo.hrank.service.mapper;

import es.hugoalvarezajenjo.hrank.domain.Team;
import es.hugoalvarezajenjo.hrank.repository.entities.TeamEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    TeamEntity toEntity(Team team);

    Team toModel(TeamEntity entity);
}
