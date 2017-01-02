import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "general_statistics")
@GenericGenerator(name = "gen", strategy = "foreign", parameters = {@Parameter(name = "property", value = "player")})
class GeneralStatistics implements Serializable {
    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    private static final long serialVersionUID = 3338678994178644199L;

    @Id
    @GeneratedValue(generator = "gen")
    @Column(name = "player_id", columnDefinition = "MEDIUMINT UNSIGNED")
    int id;

    @Column(columnDefinition = "TINYINT UNSIGNED")
    private byte level;

    @Column(columnDefinition = "BOOLEAN")
    private boolean isBanned;

    @Column
    private String clan;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
