import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Lenovo on 15-4-27.
 */
public class Reflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Target target = new Target();
        Class clazz = Target.class;

        Method m1 = clazz.getDeclaredMethod("print");
        Method m2 = clazz.getDeclaredMethod("privateMethod");
        //压制Java对访问修饰符的检查
        m2.setAccessible(true);
        Method m3 = clazz.getDeclaredMethod("printString", String.class);
        Method m4 = clazz.getDeclaredMethod("getParam");
        m1.invoke(target);
        m2.invoke(target);
        m3.invoke(target, "hehe");
        System.out.println(m4.invoke(target));
        //访问私有属性
        Field field = clazz.getDeclaredField("param");
        field.setAccessible(true);
        field.set(target, Integer.MAX_VALUE);
        System.out.println(m4.invoke(target));
    }


}
