package ramesh.aadhavan.misc;

public class CompareVersions {
    public int compareVersion(String version1, String version2) {
        String[] tokens1 = _tokenize(version1);
        String[] tokens2 = _tokenize(version2);
        int len1 = tokens1.length;
        int len2 = tokens2.length;

        int min = Math.min(len1, len2);

        int firstToken = Integer.compare(Integer.valueOf(tokens1[0]), Integer.valueOf(tokens2[0]));
        if(firstToken != 0)
            return firstToken;

        for(int i=1; i<min; i++) {
            int compare = _compare(tokens1[i], tokens2[i]);
            if(compare != 0)
                return compare;
        }

        if(len1 == len2)
            return 0;
        else {
            int result = len1 > len2 ? 1 : -1;
            String[] bigger = result == 1 ? tokens1 : tokens2;

            for(int i=min; i<bigger.length; i++) {
                if(!_isZeroes(bigger[i]))
                    return result;
            }

            return 0;
        }
    }

    private String[] _tokenize(String version) {
        int dots = (int) version.chars().filter(c -> c == '.').count();
        String[] tokens = new String[dots+1];
        StringBuilder sb = new StringBuilder();
        int i=0;
        for(char c : version.toCharArray()) {
            if(c == '.') {
                tokens[i] = sb.toString();
                sb = new StringBuilder();
                i++;
            }
            else {
                sb.append(c);
            }
        }

        tokens[i] = sb.toString();
        return tokens;
    }

    private int _compare(String version1, String version2) {
        return Integer.compare(Integer.valueOf(version1), Integer.valueOf(version2));
    }

    private boolean _isZeroes(String s) {
        for(int i=0; i<s.length(); i++)
            if(s.charAt(i) != '0')
                return false;

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new CompareVersions().compareVersion("1.1", "1.01"));
    }
}
