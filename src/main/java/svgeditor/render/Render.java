package svgeditor.render;
import svgeditor.graphics.Elipse;
import svgeditor.graphics.Geometry;
import svgeditor.graphics.Rectangle;
import svgeditor.graphics.Segment;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement(name = "svg")
@XmlSeeAlso({Rectangle.class, Elipse.class, Segment.class})
public class Render {

    private List<Geometry> list = new ArrayList<>();
    @XmlAttribute
    private String viewBox = "0 0 1000 1000";

    /**
     * přidání hodnot do listu
     */
    public Render() {
        list.add(new Rectangle(0, 0, 300, 400, 50, "#5f55ff"));
        list.add(new Elipse(100,100,400,400,50, "#5f55ff"));
        list.add(new Segment(100,100,200,200, 500, "#123456"));
    }

    @XmlElementWrapper(name="g")
    @XmlAnyElement(lax = true)
    public List<Geometry> getList() {
        return list;
    }


}
