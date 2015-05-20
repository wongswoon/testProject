/**
 * Created by Lenovo on 15-4-19.
 */
public class FaceFilter implements Filter {


    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.requestStr = request.getRequestStr().replace(":)",
                "^V^-------FaceFilter");
        chain.doFilter(request, response, chain);
        response.responseStr += "-------FaceFilter";

    }


}
