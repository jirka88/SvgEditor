package svgeditor.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import svgeditor.render.Render;

public class JsonUtils {
    public static String getJson(Render render) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.toJson(render.getList());
    }
}
