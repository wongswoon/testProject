import java.sql.Array;
import java.util.*;

/**
 * Created by Lenovo on 15-4-21.
 */
public class Test {
    public static void main(String[] args) {
        String s = "010010";
        System.out.println(JsonUtil.toJson(restoreIpAddresses(s)));

    }

    static boolean isValid(String s) {
        if (s == null || s.length() == 0 || s.length() > 3)
            return false;
        if (s.length() > 1 && s.charAt(0) == '0')
            return false;
        Integer i = Integer.valueOf(s);
        if (i >= 0 && i <= 255) return true;
        return false;
    }


    static void search(String s, int pos[], int cur, int n, int cnt) {
        if (cur == n || cnt > 3) {
            String ip1 = s.substring(0, pos[1] + 1)
                    .concat(".");
            String ip2 = s.substring(pos[1] + 1, pos[2] + 1).concat(".");

            String ip3 = s.substring(pos[2] + 1, pos[3] + 1).concat(".");

            String ip4 = s.substring(pos[3] + 1);

            if (isValid(ip4))
                ips.add(ip1.concat(ip2).concat(ip3).concat(ip4));

            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (cur + i >= n)
                continue;
            String str = s.substring(cur + 1, cur + i + 1);
            if (isValid(str)) {
                pos[cnt] = cur;
                search(s, pos, cur + i, n, cnt + 1);

            }
        }
    }

    static Set<String> ips = new HashSet<String>();

    static public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() == 0 || s.length() > 12 || s.length() < 4)
            return new ArrayList<String>();

        int pos[] = new int[4];
        search(s, pos, -1, s.length(), 0);
        return new ArrayList<String>(ips);

    }


}
