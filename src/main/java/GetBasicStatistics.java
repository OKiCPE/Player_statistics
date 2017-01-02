import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class GetBasicStatistics implements Runnable {
    private static final Logger log = LogManager.getLogger(GetBasicStatistics.class);
    private static final String BASE_URL = "http://warthunder.com/en/community/userinfo/?nick=";
    private String url;
    private Player player;
    Document webPage;

    GetBasicStatistics(Player player) {
        this.player = player;
        this.url = BASE_URL + player.getLogin();
    }

    void parce() {

        try {
            webPage = Jsoup.connect(url).timeout(10000).get();
        } catch (IOException e) {
            log.error("Error parsing page " + e);
        }

    }

    @Override
    public void run() {
        parce();
    }
}
