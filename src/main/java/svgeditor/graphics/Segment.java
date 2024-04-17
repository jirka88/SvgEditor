package svgeditor.graphics;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;
@XmlRootElement(name="segment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Segment implements Geometry{
    @XmlAttribute
    private double x =0;
    @XmlAttribute
    private double y =0;
    @XmlAttribute
    private double x2 = 0;
    @XmlAttribute
    private double y2 = 0;
    @XmlAttribute
    private float width = 0;
    @XmlAttribute
    private String color = "#000000";

    /**
     * Vytvoří úsečku
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @param width
     * @param color
     */
    public Segment(double x, double y, double x2, double y2, float width, String color) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.width = width;
        this.color = color;
    }

    public Segment() {
    }

    @Override
    public void Draw(Graphics2D g) {
        g.setColor(Color.decode(this.color));
        g.setStroke(new BasicStroke(this.width));
        g.drawLine((int)x,(int)y,(int) x2,(int) y2);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public String getColor() {
        return color;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
