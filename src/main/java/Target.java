import javax.annotation.Resource;

/**
 * Created by Lenovo on 15-4-27.
 */
public class Target {
    @Resource
    int param;

/*    public Target(int param) {
        this.param = param;
    }*/

    public int getParam() {
        return param;
    }

    public void print(){
        System.out.print("print call: param=");
        System.out.println(param);
    }
    public void printString(String s){
        System.out.print("printString call: s=");
        System.out.println(s);
    }
    private void privateMethod(){
        System.out.println("privateMethod call");
    }

    public static void staticMethod(){
        System.out.println("staticMethod call");
    }
}
