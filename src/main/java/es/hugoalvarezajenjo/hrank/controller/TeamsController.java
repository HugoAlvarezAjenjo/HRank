package es.hugoalvarezajenjo.hrank.controller;

import es.hugoalvarezajenjo.hrank.controller.dto.TeamsWrapper;
import es.hugoalvarezajenjo.hrank.domain.CompetitionConfig;
import es.hugoalvarezajenjo.hrank.domain.ProgressPhase;
import es.hugoalvarezajenjo.hrank.domain.Team;
import es.hugoalvarezajenjo.hrank.service.CompetitionService;
import es.hugoalvarezajenjo.hrank.service.NotificationService;
import es.hugoalvarezajenjo.hrank.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class TeamsController {
    private final TeamService teamService;
    private final NotificationService notificationService;
    private final CompetitionService competitionService;

    @GetMapping
    public String showAdminPanel(Model model) {
        model.addAttribute("teamsWrapper", new TeamsWrapper(this.teamService.findAll()));
        model.addAttribute("phases", ProgressPhase.values());
        model.addAttribute("newTeam", new Team());
        model.addAttribute("competitionConfig", competitionService.getConfig());
        return "admin-panel";
    }

    @PostMapping("/config")
    public String updateConfig(@ModelAttribute CompetitionConfig config) {
        competitionService.saveConfig(config);
        notificationService.notifyDisplaysToUpdate();
        return "redirect:/admin";
    }

    @PostMapping("/save")
    public String updateAllTeams(@ModelAttribute TeamsWrapper teamsWrapper) {
        if (teamsWrapper.getTeams() != null) {
            for (Team team : teamsWrapper.getTeams()) {
                this.teamService.save(team);
            }
        }
        this.notificationService.notifyDisplaysToUpdate();
        return "redirect:/admin";
    }

    @PostMapping("/create")
    public String createTeam(@ModelAttribute("newTeam") Team team) {
        team.setPercentage(0);
        team.setPhase(ProgressPhase.REQUISITOS);
        this.teamService.save(team);
        this.notificationService.notifyDisplaysToUpdate();
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String deleteTeamForm(final Model model) {
        model.addAttribute("teams", this.teamService.findAll());
        return "admin-delete";
    }

    @PostMapping("/delete-team/{id}")
    public String deleteTeamFromPanel(@PathVariable Long id) {
        this.teamService.deleteById(id);
        this.notificationService.notifyDisplaysToUpdate();
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteTeam(@PathVariable Long id) {
        this.teamService.deleteById(id);
        this.notificationService.notifyDisplaysToUpdate();
        return "redirect:/admin/delete";
    }
}
