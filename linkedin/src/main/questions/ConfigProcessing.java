package questions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class ConfigProcessing {
    static class Config {

        Map<String, String> map;
        Map<String, String> params;

        public Config(Map<String, String> map, Map<String, String> params) {
            this.map = map;
            this.params = params;
        }

        public Map<String, String> getMap() {
            return map;
        }
    }

    public enum ConfigType {
        GLOBAL,
        APPLICATION;
    }

    private final EnumMap<ConfigType, Config> configMap;

    public ConfigProcessing() {
        configMap = new EnumMap<>(ConfigType.class);
    }

    public void registerConfigFile(ConfigType configType, String absoluteFilePath) throws IOException {
        File file = new File(absoluteFilePath);
        Map<String, String> map = new HashMap<>();

        if(file.exists()) {
            try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                Pattern pattern = Pattern.compile("([a-zA-Z0-9]+)=([a-zA-Z0-9]+)");

                while((line = reader.readLine()) != null) {
                    if(!pattern.matcher(line).matches())
                        continue;

                    String[] entry = line.split("=");
                    map.put(entry[0], entry[1]);
                }
            }

            registerConfig(configType, new Config(map, new HashMap<>()));
        }
    }

    private void registerConfig(ConfigType configType, Config config) {
        configMap.put(configType, config);
    }

    public Map<String, String> getConfig(ConfigType configType) {
        if(configMap.containsKey(configType))
            return configMap.get(configType).map;

        return Collections.emptyMap();
    }

    public Map<String, String> mergeConfigs() {
        Map<String, String> resultantMap = new HashMap<>();
        Map<String, String> applicationConfigMap = configMap.get(ConfigType.APPLICATION).map;
        configMap.get(ConfigType.GLOBAL)
                .map
                .forEach((key, value) -> resultantMap.put(key, applicationConfigMap.getOrDefault(key, value)));

        applicationConfigMap.forEach(resultantMap::putIfAbsent);
        return resultantMap;
    }
}
