import java.util.*;
import java.util.concurrent.*;

/**
 * Created by wangshun on 15-7-6.
 */
public class MuiltThreadsTest {
    static ExecutorService pool = Executors.newCachedThreadPool();


    static Set<Long> result = new HashSet<Long>();
    static List<Future<Set<Long>>> futrueList = new ArrayList<Future<Set<Long>>>();
    static List<Worker> tasksList = new ArrayList<Worker>();

    static Set<Long>  doAsync() {
        for (int i = 1; i <= 3; i++) {
            Worker worker = new Worker(i);
            tasksList.add(worker);
        }

        try {

            futrueList = pool.invokeAll(tasksList, 200, TimeUnit.MILLISECONDS);

        } catch (InterruptedException e1) {

        }

        for (Future<Set<Long>> ftask : futrueList) {
            try {
                if (ftask.isDone() && !ftask.isCancelled()) {
                    // invokeAll 方法会把全部未完成的任务 cancel 掉，因此此处只需要看完成的，并且不是被中止执行的任务。否则会抛出 CancellationException 异常。
                    Set<Long> ret = ftask.get();
                    if (ret != null) {
                        result.addAll(ret);
                    }
                }
            } catch (final Exception e) {

            }
        }
        return result;
    }

    public static void main(String[] args) {
        Set<Long> set = doAsync();
        System.out.println(set);
    }
}
