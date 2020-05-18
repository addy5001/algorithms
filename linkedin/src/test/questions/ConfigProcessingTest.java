package questions;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class ConfigProcessingTest {

    @Test
    public void testConfigProcessing() throws IOException {
        String global = "src/test/resources/config.global";
        String application = "src/test/resources/config.application";

        ConfigProcessing configProcessing = new ConfigProcessing();
        configProcessing.registerConfigFile(ConfigProcessing.ConfigType.GLOBAL, global);
        configProcessing.registerConfigFile(ConfigProcessing.ConfigType.APPLICATION, application);
        Map<String, String> finalConfigs = configProcessing.mergeConfigs();

        Assert.assertEquals(6, finalConfigs.size());
        Assert.assertEquals("prod", finalConfigs.get("env"));
        Assert.assertEquals("maximum", finalConfigs.get("encryption"));
        Assert.assertEquals("utility", finalConfigs.get("namespace"));
        Assert.assertEquals("sha1", finalConfigs.get("hash"));
        Assert.assertEquals("logging", finalConfigs.get("applicationName"));
        Assert.assertEquals("5", finalConfigs.get("hosts"));
    }
}
