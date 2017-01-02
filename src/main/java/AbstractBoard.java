import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AbstractBoard implements Serializable {
    private static final long serialVersionUID = 6356597109223107258L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "MEDIUMINT UNSIGNED")
    int id;
    @Column(columnDefinition = "SMALLINT UNSIGNED")
    int place;
    @Column(columnDefinition = "SMALLINT UNSIGNED")
    int victories;
    @Column(columnDefinition = "SMALLINT UNSIGNED")
    int missions;
    @Column(columnDefinition = "SMALLINT UNSIGNED")
    int sessions;
    @Column(columnDefinition = "SMALLINT UNSIGNED")
    int deaths;
    @Column(name = "air_frags", columnDefinition = "SMALLINT UNSIGNED")
    int airTargetDestroyed;
    @Column(name = "ground_frags", columnDefinition = "SMALLINT UNSIGNED")
    int groundTargetDestroyed;
    @Column(name = "win_rate", columnDefinition = "FLOAT")
    float winRate;
    @Column(name = "air_frags_in_session", columnDefinition = "FLOAT")
    float airFragsInSession;
    @Column(name = "ground_frags_in_session", columnDefinition = "FLOAT")
    float groundFragsInSession;
    @Column(name = "sessions_in_mission", columnDefinition = "FLOAT")
    float sessionaInMission;
    @OneToOne
    @JoinColumn(unique = true, nullable = false, name = "player_id", referencedColumnName = "id")
    private Player player;

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }

    public int getMissions() {
        return missions;
    }

    public void setMissions(int missions) {
        this.missions = missions;
    }

    public int getSessions() {
        return sessions;
    }

    public void setSessions(int sessions) {
        this.sessions = sessions;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAirTargetDestroyed() {
        return airTargetDestroyed;
    }

    public void setAirTargetDestroyed(int airTargetDestroyed) {
        this.airTargetDestroyed = airTargetDestroyed;
    }

    public int getGroundTargetDestroyed() {
        return groundTargetDestroyed;
    }

    public void setGroundTargetDestroyed(int groundTargetDestroyed) {
        this.groundTargetDestroyed = groundTargetDestroyed;
    }

    public float getWinRate() {
        return winRate;
    }

    public void setWinRate(float winRate) {
        this.winRate = winRate;
    }

    public float getAirFragsInSession() {
        return airFragsInSession;
    }

    public void setAirFragsInSession(float airFragsInSession) {
        this.airFragsInSession = airFragsInSession;
    }

    public float getGroundFragsInSession() {
        return groundFragsInSession;
    }

    public void setGroundFragsInSession(float groundFragsInSession) {
        this.groundFragsInSession = groundFragsInSession;
    }

    public float getSessionaInMission() {
        return sessionaInMission;
    }

    public void setSessionaInMission(float sessionInMission) {
        this.sessionaInMission = sessionInMission;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    @Override
    public int hashCode() {
        return player.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractBoard that = (AbstractBoard) o;

        return player.equals(that.player);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void calculate() {
        if (missions > 0) {
            winRate = (float) victories / (float) missions;
            sessionaInMission = (float) sessions / (float) missions;
        }
        if (sessions > 0) {
            airFragsInSession = (float) airTargetDestroyed / (float) sessions;
            groundFragsInSession = (float) groundTargetDestroyed / (float) sessions;
        }
        if (winRate == 0) {

            winRate = (float) victories / (float) missions;
        }
    }

}
