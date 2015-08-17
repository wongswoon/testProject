import java.io.IOException;

public class Proc {
    public static void main(String[] args) {
        Runtime run = Runtime.getRuntime();
        try {
            int i = 1000;
            while (i-- > 0) {
                Process p = run.exec("java LoadBreaker");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}