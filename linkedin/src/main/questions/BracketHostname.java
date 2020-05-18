package questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BracketHostname {

    public List<String> parseHosts(String encodedHostname) {
        if(encodedHostname == null || encodedHostname.isBlank())
            return Collections.emptyList();

        Pattern pattern = Pattern.compile("([a-z])*\\(([0-9]*)(,[0-9]*)*\\)\\.([a-z])*");
        Matcher matcher = pattern.matcher(encodedHostname);
        if(!matcher.matches())
            return Collections.emptyList();

        String[] tokens = encodedHostname.split("\\.");
        String env = tokens[1];

        String[] appTokens = tokens[0].split("\\(");
        String app = appTokens[0];

        String[] hostNumbers = appTokens[1].split("\\)")[0].split(",");

        List<String> results = new ArrayList<>();
        for(String hostNumber : hostNumbers) {
            results.add(app.concat(hostNumber).concat(".").concat(env));
        }

        return results;
    }

    /**
     * Follow up
     *
     * Ranges app(1-10,13,19).prod
     * Regex to validate: ([a-z])+\(([0-9]*(-[0-9]+)?)(,[0-9]*(-[0-9]+)?)*\)\.([a-z])+
     */
}
