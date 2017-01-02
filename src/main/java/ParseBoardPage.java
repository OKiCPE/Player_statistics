import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ParseBoardPage implements Runnable {
    private static final Logger log = LogManager.getLogger(ParseBoardPage.class);
    private int pagenumber;
    private final static String BASE_URL = "http://warthunder.com/en/community/leaderboard/?sort=battles&value=month&page=";
    private String url;
    private Document webPage;
    private GameMode gameMode;

    ParseBoardPage(int pagenumber, GameMode gameMode) {
        this.pagenumber = pagenumber;
        this.gameMode = gameMode;
    }


    void loadPage() {

        StringBuilder stringBuilder = new StringBuilder(BASE_URL);
        stringBuilder.append(Integer.toString(pagenumber));
        switch (this.gameMode) {
            case Arcade:
                stringBuilder.append("&game=arcade");
                break;
            case Realistic:
                stringBuilder.append("&game=historical");
                break;
            case Simulator:
                stringBuilder.append("&game=simulation");
                break;
        }
        url = stringBuilder.toString();

        try {
            webPage = Jsoup.connect(url).timeout(10000).get();
        } catch (IOException e) {
            log.error("Error parsing page " + e);
        }
    }


    void parse() {
        Elements playerElements = webPage.select("a[class=player_on_lb]");
        for (Element player : playerElements) {
            synchronized (player) {
                SaveOrUpdatePlayer saveOrUpdatePlayer = new SaveOrUpdatePlayer(player.parent().parent(), gameMode);
                Thread thread = new Thread(saveOrUpdatePlayer);
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    log.error("Rhread was interrupted " + e);
                }
            }

        }
    }


    @Override
    public void run() {
        loadPage();
        parse();
    }
}
