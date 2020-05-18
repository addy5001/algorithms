package questions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPAddressSearch {

    /**
     * public static boolean validIP (String ip) {
     *     try {
     *         if ( ip == null || ip.isEmpty() ) {
     *             return false;
     *         }
     *
     *         String[] parts = ip.split( "\\." );
     *         if ( parts.length != 4 ) {
     *             return false;
     *         }
     *
     *         for ( String s : parts ) {
     *             int i = Integer.parseInt( s );
     *             if ( (i < 0) || (i > 255) ) {
     *                 return false;
     *             }
     *         }
     *         if ( ip.endsWith(".") ) {
     *             return false;
     *         }
     *
     *         return true;
     *     } catch (NumberFormatException nfe) {
     *         return false;
     *     }
     * }
     * @param logFile
     * @return
     * @throws IOException
     */

    public List<String> parseIpAddresses(File logFile) throws IOException {
        Objects.requireNonNull(logFile);
        if(!logFile.exists())
            throw new FileNotFoundException(String.format("%s does not exist", logFile.getAbsolutePath()));

        List<String> allMatches = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(logFile))) {
            Pattern pattern = Pattern
                    .compile("(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}");

            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);

                while(matcher.find()) {
                    allMatches.add(matcher.group());
                }
            }
        }

        return allMatches;
    }

    /**
     * Follow up:
     *
     * IPv6: eight groups, separated by colons, of four hexadecimal digits
     * IPv6 uses a 128-bit address, theoretically allowing 2128, or approximately 3.4×1038 addresses
     */
}
