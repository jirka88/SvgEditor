package svgeditor.render;

import svgeditor.graphics.Elipse;
import svgeditor.graphics.Geometry;
import svgeditor.graphics.Rectangle;
import svgeditor.graphics.Section;

import java.util.ArrayList;
import java.util.List;

public class Render {

    private List<Geometry> list = new ArrayList<>();

    /**
     * přidání hodnot do listu
     */
    public Render() {
        list.add(new Rectangle(100, 100, 300, 400, 50, "#5f55ff"));
        list.add(new Elipse(100,100,400,400,50, "#5f55ff"));
        list.add(new Section(100,100,200,200, "#123456"));
    }
    public List<Geometry> getList() {
        return list;
    }
}
