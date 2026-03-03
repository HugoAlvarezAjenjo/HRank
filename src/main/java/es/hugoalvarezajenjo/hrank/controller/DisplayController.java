package es.hugoalvarezajenjo.hrank.controller;

import es.hugoalvarezajenjo.hrank.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DisplayController {

    private final CompetitionService competitionService;

    @GetMapping("/")
    public String showEntrance(Model model) {
        model.addAttribute("competitionConfig", competitionService.getConfig());
        return "index";
    }

    @GetMapping("/scoreboard")
    public String showScoreboard(Model model) {
        model.addAttribute("competitionConfig", competitionService.getConfig());
        return "scoreboard";
    }
}
