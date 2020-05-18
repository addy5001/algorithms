package ramesh.aadhavan.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestoreIpAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<List<String>> ipAddresses = new ArrayList<>();
        _dfs(s, 0, new ArrayList<>(), ipAddresses);
        return ipAddresses.stream().map(ipList -> String.join(".", ipList)).collect(Collectors.toList());
    }

    private void _dfs(String s, int idx, List<String> ipAdress, List<List<String>> allIpAddresses) {
        if(ipAdress.size() >= 4 && idx != s.length())
            return;

        if((ipAdress.size() + s.length() - idx + 1) < 4)
            return;

        if(ipAdress.size() == 4) {
            allIpAddresses.add(new ArrayList<>(ipAdress));
            return;
        }

        for(int i=1; i<=3; i++) {
            if(idx + i > s.length())
                break;

            String octet = s.substring(idx, idx+i);
            if(_isValidOctet(octet)) {
                ipAdress.add(octet);
                _dfs(s, idx+i, ipAdress, allIpAddresses);
                ipAdress.remove(ipAdress.size()-1);
            }
            else {
                break;
            }
        }
    }

    private boolean _isValidOctet(String s) {
        int octet = Integer.parseInt(s);
        return s.charAt(0) == '0' ? s.length() == 1 : octet > 0 && octet <= 255;
    }
}
