package es.hugoalvarezajenjo.hrank.controller;

import es.hugoalvarezajenjo.hrank.domain.ProgressPhase;
import es.hugoalvarezajenjo.hrank.domain.Team;
import es.hugoalvarezajenjo.hrank.service.NotificationService;
import es.hugoalvarezajenjo.hrank.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/team/{accessKey}")
@RequiredArgsConstructor
public class TeamPanelController {

    private final TeamService teamService;
    private final NotificationService notificationService;

    @GetMapping
    public String showTeamPanel(@PathVariable String accessKey, Model model) {
        Team team = teamService.findByAccessKey(accessKey);
        if (team == null) {
            return "redirect:/?error=invalid_code";
        }
        model.addAttribute("team", team);
        model.addAttribute("phases", ProgressPhase.values());
        return "team-panel";
    }

    @PostMapping("/update")
    public String updateTeamProgress(@PathVariable String accessKey,
                                   @ModelAttribute Team teamUpdate) {
        Team team = teamService.findByAccessKey(accessKey);
        if (team != null) {
            team.setPhase(teamUpdate.getPhase());
            team.setPercentage(teamUpdate.getPercentage());
            teamService.save(team);
            notificationService.notifyDisplaysToUpdate();
        }
        return "redirect:/team/" + accessKey + "?success=true";
    }
}
