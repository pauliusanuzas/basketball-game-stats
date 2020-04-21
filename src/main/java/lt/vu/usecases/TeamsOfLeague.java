package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.League;
import lt.vu.entities.Team;
import lt.vu.persistence.LeaguesDAO;
import lt.vu.persistence.TeamsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Model
public class TeamsOfLeague {

    @Inject
    private LeaguesDAO leaguesDAO;

    @Inject
    private TeamsDAO teamsDAO;

    @Getter
    private Team teamToAdd = new Team();

    @Getter @Setter
    private League league;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer leagueId = Integer.parseInt(requestParameters.get("leagueId"));
        this.league = leaguesDAO.findOne(leagueId);
    }

    @Transactional
    public String createTeam(){
        teamToAdd.setLeagues(Collections.singletonList(this.league));
        this.league.getParticipatingTeams().add(teamToAdd);

        leaguesDAO.persist(this.league);
        teamsDAO.persist(teamToAdd);
        return "teams?faces-redirect=true&leagueId=" + this.league.getId();
    }
}
