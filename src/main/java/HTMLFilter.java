/**
 * Created by Lenovo on 15-4-19.
 */
public class HTMLFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.requestStr = request.getRequestStr().replace("<", "[")
                .replace(">", "] --------HTMLFilter");
        chain.doFilter(request, response, chain);
        response.responseStr += "--------HTMLFilter";
    }
}
