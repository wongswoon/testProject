/**
 * Created by Lenovo on 15-4-19.
 */
public class SensitiveFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.requestStr = request.getRequestStr().replace("敏感", "  ")
                .replace("猫猫", "haha------SesitiveFilter");
        chain.doFilter(request, response, chain);
        response.responseStr += "------SesitiveFilter";

    }
}
