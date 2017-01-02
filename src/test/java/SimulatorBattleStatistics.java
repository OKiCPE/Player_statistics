import org.junit.Assert;
import org.junit.Test;

public class SimulatorBattleStatistics {
    @Test
    public void createEntity() {
        SimulatorStatistics sb = new SimulatorStatistics();
        Assert.assertNotNull(sb);
    }
}
