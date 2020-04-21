package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "League.findAll", query = "select l from League as l")
})
@Table(name = "LEAGUE")
@Getter @Setter
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "LEAGUE_TEAM",
            joinColumns = @JoinColumn(name = "LEAGUE_ID"),
            inverseJoinColumns = @JoinColumn(name = "TEAM_ID"))
    private List<Team> participatingTeams;
}
