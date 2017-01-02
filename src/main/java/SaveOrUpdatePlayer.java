import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jsoup.nodes.Element;

import javax.persistence.Query;
import java.util.List;
import java.util.regex.Pattern;

public class SaveOrUpdatePlayer implements Runnable {
    private static final Logger log = LogManager.getLogger(SaveOrUpdatePlayer.class);
    private static final Pattern firstDigit = Pattern.compile("[0-9].*");

    Element data;
    GameMode gameMode;

    SaveOrUpdatePlayer(Element element, GameMode gameMode) {
        this.data = element;
        this.gameMode = gameMode;
    }


    private int stringParser(String string) {
        int position = string.indexOf('K');
        int res = 0;
        if (firstDigit.matcher(string).matches()) {
            if (position > 0) {
                res = (int) (Float.parseFloat(string.substring(0, position)) * 1000);
            } else {
                res = Integer.parseInt(string);
            }
        }
        return res;
    }

    private float getWinRate(String string) {
        int position = string.indexOf('%');
        float res;
        if (position > 0) {
            res = Float.parseFloat(string.substring(0, position)) / 100;
        } else res = 0;
        return res;
    }


    void parsAndSave() {


        String login = data.select("a[class=player_on_lb]").text();
        Player player;
        AbstractBoard abstractBoard = null;
        ArcadeBoard arcadeBoard;
        RealisticBoard realisticBoard;
        SimulatorBoard simulatorBoard;

        Session session = HibernateFactory.getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Player where login = :login");
        query.setParameter("login", login);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            player = (Player) query.getSingleResult();
            arcadeBoard = player.getArcadeBoard();
            realisticBoard = player.getRealisticBoard();
            simulatorBoard = player.getSimulatorBoard();
            // log.error(login + " updated");
        } else {
            player = new Player(login);
            arcadeBoard = new ArcadeBoard();
            realisticBoard = new RealisticBoard();
            simulatorBoard = new SimulatorBoard();
            //log.error(login + " created");
        }


        switch (gameMode) {
            case Arcade:
                if (arcadeBoard == null) arcadeBoard = new ArcadeBoard();
                abstractBoard = arcadeBoard;
                break;
            case Realistic:
                if (realisticBoard == null) realisticBoard = new RealisticBoard();
                abstractBoard = realisticBoard;
                break;
            case Simulator:
                if (simulatorBoard == null) simulatorBoard = new SimulatorBoard();
                abstractBoard = simulatorBoard;
                break;
        }


        abstractBoard.setPlayer(player);
        abstractBoard.setPlace(stringParser(data.select("td:eq(0)").text()));
        abstractBoard.setVictories(stringParser(data.select("td:eq(2)").text()));
        float winRate = getWinRate(data.select("td:eq(4)").text());
        if ((abstractBoard.getVictories() < 1000) && (winRate != 0)) {
            abstractBoard.setMissions((int) (abstractBoard.getVictories() / winRate));
        } else {
            abstractBoard.setMissions(stringParser(data.select("td:eq(3)").text()));
        }
        abstractBoard.setSessions(stringParser(data.select("td:eq(5)").text()));
        abstractBoard.setDeaths(stringParser(data.select("td:eq(6)").text()));
        abstractBoard.setAirTargetDestroyed(stringParser(data.select("td:eq(7)").text()));
        abstractBoard.setGroundTargetDestroyed(stringParser(data.select("td:eq(8)").text()));
        abstractBoard.calculate();


        switch (gameMode) {
            case Arcade:
                player.setArcadeBoard(arcadeBoard);
                break;
            case Realistic:
                player.setRealisticBoard(realisticBoard);
                break;
            case Simulator:
                player.setSimulatorBoard(simulatorBoard);
                break;
        }


        session.saveOrUpdate(player);
        transaction.commit();
        session.close();


    }


    @Override
    public void run() {
        parsAndSave();
    }
}
