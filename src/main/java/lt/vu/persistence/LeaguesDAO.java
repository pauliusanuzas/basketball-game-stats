package lt.vu.persistence;

import lt.vu.entities.League;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class LeaguesDAO {

    @Inject
    private EntityManager em;

    public List<League> loadAll(){
        return em.createNamedQuery("League.findAll", League.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(League league){
        this.em.persist(league);
    }

    public League findOne(Integer id) {
        return em.find(League.class, id);
    }
}
