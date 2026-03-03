package es.hugoalvarezajenjo.hrank.controller;

import es.hugoalvarezajenjo.hrank.domain.CompetitionConfig;
import es.hugoalvarezajenjo.hrank.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config")
@RequiredArgsConstructor
public class ConfigRestController {

    private final CompetitionService competitionService;

    @GetMapping
    public CompetitionConfig getConfig() {
        return competitionService.getConfig();
    }
}
