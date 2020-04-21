package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Game;
import lt.vu.entities.League;
import lt.vu.entities.Team;
import lt.vu.persistence.GamesDAO;
import lt.vu.persistence.LeaguesDAO;
import lt.vu.persistence.TeamsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class GamesOfTeam {

    @Inject
    private LeaguesDAO leaguesDAO;

    @Inject
    private TeamsDAO teamsDAO;

    @Inject
    private GamesDAO gamesDAO;

    @Getter
    private Game gameToAdd = new Game();

    @Getter @Setter
    private Integer homeTeamToAdd;

    @Getter @Setter
    private Integer awayTeamToAdd;

    @Getter @Setter
    private League league;

    @Getter @Setter
    private Team team;

    @Getter
    private List<Game> allGames;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer leagueId = Integer.parseInt(requestParameters.get("leagueId"));
        Integer teamId = Integer.parseInt(requestParameters.get("teamId"));

        this.league = leaguesDAO.findOne(leagueId);
        this.team = teamsDAO.findOne(teamId);

        allGames = this.team.getAwayGames();
        allGames.addAll(this.team.getHomeGames());
    }

    @Transactional
    public String addGame(){
        gameToAdd.setHomeTeam(teamsDAO.findOne(homeTeamToAdd));
        gameToAdd.setAwayTeam(teamsDAO.findOne(awayTeamToAdd));
        if(team.getId().equals(gameToAdd.getHomeTeam().getId())){
            team.getHomeGames().add(gameToAdd);
        }
        else{
            team.getAwayGames().add(gameToAdd);
        }
        teamsDAO.persist(team);
        gamesDAO.persist(gameToAdd);
        return "games?faces-redirect=true&leagueId=" + this.league.getId() + "&teamId=" + this.team.getId();
    }
}
