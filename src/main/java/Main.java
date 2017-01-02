import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(10);
        for (GameMode gameMode : GameMode.values())
            for (int i = 0; i < 1001; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    log.error("Interrupted Exception " + e);
                }
                service.submit(new ParseBoardPage(i, gameMode));
            }
        service.shutdown();
        try {
            while (!service.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS)) {
                log.info("Awaiting completion of threads.");
            }
        } catch (InterruptedException e) {
            log.error("Thread problem");
        }
        HibernateFactory.close();
    }
}
