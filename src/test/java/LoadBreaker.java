import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by wangshun on 15-8-14.
 */
public class LoadBreaker {
    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        int num = processors;

        ExecutorService es = Executors.newFixedThreadPool(num * 500);

        while (true) {
            es.submit(new Runnable() {
                @Override
                public void run() {
                    while (true) {

                    }
                }
            });


        }


    }

}
