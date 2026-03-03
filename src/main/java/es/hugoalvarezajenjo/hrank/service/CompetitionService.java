package es.hugoalvarezajenjo.hrank.service;

import es.hugoalvarezajenjo.hrank.domain.CompetitionConfig;

public interface CompetitionService {
    CompetitionConfig getConfig();

    void saveConfig(CompetitionConfig config);
}
