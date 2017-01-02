import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "players")
public class Player implements Serializable {

    private static final long serialVersionUID = 9046387928994853195L;

    Player(String login) {
        this.login = login;
    }

    Player() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "MEDIUMINT UNSIGNED")
    private int id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player")
    private GeneralStatistics generalStatistics;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player")
    private ArcadeStatistics arcadeStatistics;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player")
    private RealisticStatistics realisticStatistics;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player")
    private SimulatorStatistics simulatorStatistics;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player")
    private ArcadeBoard arcadeBoard;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player")
    private RealisticBoard realisticBoard;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player")
    private SimulatorBoard simulatorBoard;
    @Column(length = 24, unique = true, nullable = false)
    private String login;

    public ArcadeBoard getArcadeBoard() {
        return arcadeBoard;
    }

    public void setArcadeBoard(ArcadeBoard arcadeBoard) {
        this.arcadeBoard = arcadeBoard;
    }

    public RealisticBoard getRealisticBoard() {
        return realisticBoard;
    }

    public void setRealisticBoard(RealisticBoard realisticBoard) {
        this.realisticBoard = realisticBoard;
    }

    public SimulatorBoard getSimulatorBoard() {
        return simulatorBoard;
    }

    public void setSimulatorBoard(SimulatorBoard simulatorBoard) {
        this.simulatorBoard = simulatorBoard;
    }

    public RealisticStatistics getRealisticStatistics() {
        return realisticStatistics;
    }

    public void setRealisticStatistics(RealisticStatistics realisticStatistics) {
        this.realisticStatistics = realisticStatistics;
    }

    public SimulatorStatistics getSimulatorStatistics() {
        return simulatorStatistics;
    }

    public void setSimulatorStatistics(SimulatorStatistics simulatorStatistics) {
        this.simulatorStatistics = simulatorStatistics;
    }

    public ArcadeStatistics getArcadeStatistics() {
        return arcadeStatistics;
    }

    public void setArcadeStatistics(ArcadeStatistics arcadeStatistics) {
        this.arcadeStatistics = arcadeStatistics;
    }

    public GeneralStatistics getGeneralStatistics() {
        return generalStatistics;
    }

    public void setGeneralStatistics(GeneralStatistics generalStatistics) {
        this.generalStatistics = generalStatistics;
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return login.equals(player.login);
    }

    public int getLevel() {
        return generalStatistics.getLevel();
    }

    public void setLevel(byte level) {
        generalStatistics.setLevel(level);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


}
