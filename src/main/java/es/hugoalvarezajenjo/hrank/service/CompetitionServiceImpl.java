package es.hugoalvarezajenjo.hrank.service;

import es.hugoalvarezajenjo.hrank.domain.CompetitionConfig;
import es.hugoalvarezajenjo.hrank.repository.CompetitionConfigRepository;
import es.hugoalvarezajenjo.hrank.repository.entities.CompetitionConfigEntity;
import es.hugoalvarezajenjo.hrank.service.mapper.CompetitionConfigMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionConfigRepository repository;
    private final CompetitionConfigMapper mapper;

    @Override
    public CompetitionConfig getConfig() {
        List<CompetitionConfigEntity> configs = repository.findAll();
        if (configs.isEmpty()) {
            return new CompetitionConfig();
        }
        return mapper.toDomain(configs.get(0));
    }

    @Override
    public void saveConfig(CompetitionConfig config) {
        CompetitionConfigEntity entity = mapper.toEntity(config);
        repository.save(entity);
    }
}
