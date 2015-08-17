import org.junit.*;

/**
 * Created by wangshun on 15-7-22.
 */
public class TestClass {
    @org.junit.Test
    public void test(){
        long [] a = new long[0];
        System.out.println(a==null);
        System.out.println(a.length);
        //System.out.println(a[0]);
        System.out.println((new long[1])[0]);
    }
}
