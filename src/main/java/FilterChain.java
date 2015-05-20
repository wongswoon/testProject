import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 15-4-19.
 */
public class FilterChain implements Filter {
    List<Filter> filters = new ArrayList<Filter>();
    int index = 0;

    public FilterChain addFilter(Filter f) {
        this.filters.add(f);
        return this;
    }
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        if (index == filters.size())
            return;
        Filter f = filters.get(index);
        index++;
        f.doFilter(request, response, chain);
    }

    public static void main(String[] args) {
        String message = "敏感词汇，重庆，<script> 躲猫猫 :)";
        Request request = new Request();
        request.setRequestStr(message);
        Response response = new Response();
        response.setResponseStr("response");
        FilterChain fc = new FilterChain();
        fc.addFilter(new HTMLFilter()).addFilter(new SensitiveFilter());

        FilterChain fc2 = new FilterChain();
        fc2.addFilter(new FaceFilter());
        fc.addFilter(fc2);
        fc.doFilter(request, response,fc);
        System.out.println("request = " + request.getRequestStr());
        System.out.println("response = " + response.getResponseStr());
    }
}
