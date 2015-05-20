/**
 * Created by Lenovo on 15-4-19.
 */
public interface Filter {
    public void doFilter(Request request, Response response, FilterChain chain);
}
