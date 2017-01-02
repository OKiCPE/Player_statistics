import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class AbstractBattle implements Serializable {

    private static final long serialVersionUID = 8223143055691102355L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "MEDIUMINT UNSIGNED")
    int id;

    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id", unique = true, nullable = false)
    private Player player;

    @Override
    public int hashCode() {
        return player.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractBattle that = (AbstractBattle) o;

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

}
