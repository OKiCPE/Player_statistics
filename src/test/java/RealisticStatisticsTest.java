import org.junit.Assert;
import org.junit.Test;

public class RealisticStatisticsTest {
    @Test
    public void createEntity() {
        RealisticStatistics rb = new RealisticStatistics();
        Assert.assertNotNull(rb);
    }
}
