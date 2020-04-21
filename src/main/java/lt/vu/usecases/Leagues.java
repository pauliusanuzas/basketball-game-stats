package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.League;
import lt.vu.persistence.LeaguesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Leagues {

    @Inject
    private LeaguesDAO leaguesDAO;

    @Getter @Setter
    private League leagueToAdd = new League();

    @Getter @Setter
    private List<League> allLeagues;

    @PostConstruct
    public void init(){ loadAllLeagues(); }

    @Transactional
    public String createLeague(){
        this.leaguesDAO.persist(leagueToAdd);
        return "index?faces-redirect=true";
    }

    private void loadAllLeagues(){
        this.allLeagues = leaguesDAO.loadAll();
    }
}
