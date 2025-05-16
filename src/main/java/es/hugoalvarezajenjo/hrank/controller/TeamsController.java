package es.hugoalvarezajenjo.hrank.controller;

import es.hugoalvarezajenjo.hrank.controller.dto.TeamsWrapper;
import es.hugoalvarezajenjo.hrank.domain.ProgressPhase;
import es.hugoalvarezajenjo.hrank.domain.Team;
import es.hugoalvarezajenjo.hrank.service.NotificationService;
import es.hugoalvarezajenjo.hrank.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class TeamsController {
    private final TeamService teamService;
    private final NotificationService notificationService;

    @GetMapping
    public String showAdminPanel(Model model) {
        model.addAttribute("teamsWrapper", new TeamsWrapper(this.teamService.findAll()));
        model.addAttribute("phases", ProgressPhase.values());
        model.addAttribute("newTeam", new Team());
        return "admin-panel";
    }

    @PostMapping("/save")
    public String updateAllTeams(@ModelAttribute TeamsWrapper teamsWrapper) {
        for (Team team : teamsWrapper.getTeams()) {
            this.teamService.save(team);
        }
        notificationService.notifyDisplaysToUpdate();
        return "redirect:/admin";
    }

    @PostMapping("/create")
    public String createTeam(@ModelAttribute("newTeam") Team team) {
        team.setPercentage(0);
        team.setPhase(ProgressPhase.REQUISITOS);
        this.teamService.save(team);
        notificationService.notifyDisplaysToUpdate();
        return "redirect:/admin";
    }
}
