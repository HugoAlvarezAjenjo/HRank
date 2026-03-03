package es.hugoalvarezajenjo.hrank.service.mapper;

import es.hugoalvarezajenjo.hrank.domain.CompetitionConfig;
import es.hugoalvarezajenjo.hrank.repository.entities.CompetitionConfigEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetitionConfigMapper {
    CompetitionConfig toDomain(CompetitionConfigEntity entity);

    CompetitionConfigEntity toEntity(CompetitionConfig domain);
}
