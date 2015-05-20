import com.google.common.base.Throwables;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Lenovo on 15-4-22.
 */
public class JsonUtil {



        // It is fully commutable and thread safe.
        private static final ObjectMapper MAPPER = new ObjectMapper();

        public static <T> T json2Type(String json, Class<T> valueType) {
            T t = null;
            try {
                t = MAPPER.readValue(json, valueType);
            } catch (IOException e) {

                throw Throwables.propagate(e);
            }
            return t;
        }

        public static String extractKey(String json, String key) {
            Map<String, String> map = json2Type(json, Map.class);
            return map != null ? map.get(key) : null;
        }

        public static String writeValueAsString(Object o) {
            String s = null;
            try {
                s = MAPPER.writeValueAsString(o);
            } catch (IOException e) {

            }
            return s;
        }

        public static String toJson(Object o) {
            return writeValueAsString(o);
        }

        public static <T> boolean isNullOrEmpty(Collection<T> c) {
            return (c == null || c.isEmpty());
        }

}
