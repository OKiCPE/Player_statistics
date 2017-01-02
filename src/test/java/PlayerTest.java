import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.*;

import javax.persistence.Query;

public class PlayerTest {
    static Player player;
    static Session session;
    static Transaction transaction;
    static Query query;

    @BeforeClass
    public static void prepare() {
        session = HibernateFactory.getSession();
        transaction = session.beginTransaction();

    }

    @AfterClass
    public static void close() {
        if (session != null) session.close();
        HibernateFactory.close();
    }


    private Player createEntity() {
        player = new Player("A");
        GeneralStatistics generalStatistics = new GeneralStatistics();
        ArcadeStatistics arcadeStatistics = new ArcadeStatistics();
        RealisticStatistics realisticStatistics = new RealisticStatistics();
        SimulatorStatistics simulatorStatistics = new SimulatorStatistics();
        ArcadeBoard arcadeBoard = new ArcadeBoard();
        RealisticBoard realisticBoard = new RealisticBoard();
        SimulatorBoard simulatorBoard = new SimulatorBoard();
        player.setLogin("TestPlayer");
        player.setGeneralStatistics(generalStatistics);
        generalStatistics.setPlayer(player);
        player.setArcadeStatistics(arcadeStatistics);
        arcadeStatistics.setPlayer(player);
        player.setRealisticStatistics(realisticStatistics);
        realisticStatistics.setPlayer(player);
        player.setSimulatorStatistics(simulatorStatistics);
        simulatorStatistics.setPlayer(player);
        player.setArcadeBoard(arcadeBoard);
        arcadeBoard.setPlayer(player);
        player.setRealisticBoard(realisticBoard);
        realisticBoard.setPlayer(player);
        player.setSimulatorBoard(simulatorBoard);
        simulatorBoard.setPlayer(player);

        return player;
    }

    @Ignore
    @Test
    public void readEntity() {

        Query query = session.createQuery("from Player where login = :login");
        query.setParameter("login", "TestPlayer");
        if (query.getSingleResult() != null) {
            player = (Player) query.getSingleResult();
            Assert.assertNotNull(player);
            session.delete(player);
        } else {

        }
    }


    @Test
    public void saveEntity() {
        player = createEntity();
        session.saveOrUpdate(player);
        transaction.commit();
    }
}
