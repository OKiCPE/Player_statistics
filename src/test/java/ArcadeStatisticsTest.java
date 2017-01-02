import org.junit.Assert;
import org.junit.Test;

public class ArcadeStatisticsTest {
    @Test
    public void createEntity() {
        ArcadeStatistics arcade = new ArcadeStatistics();
        Assert.assertNotNull(arcade);
    }
}
