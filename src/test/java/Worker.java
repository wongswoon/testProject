import com.google.common.collect.Sets;

import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Created by wangshun on 15-7-6.
 */
public class Worker implements Callable<Set<Long>> {
    long i;
    Set<Long > set =  Sets.newHashSet();
    public Worker(long i) {
        this.i = i;
    }

    @Override
    public Set<Long> call() throws Exception {
        set.add(i);
        return set;
    }
}
