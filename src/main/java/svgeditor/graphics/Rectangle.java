package svgeditor.graphics;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;
@XmlRootElement(name="rectangle")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rectangle implements Geometry{
    @XmlAttribute
    private double x = 0;
    @XmlAttribute
    private double y = 0;
    @XmlAttribute
    private double width = 0;
    @XmlAttribute
    private double height = 0;
    @XmlAttribute(name="background-color")
    private String backgroundColor = "";
    @XmlAttribute(name="stroke-width")
    private float strokeWidth = 0f;
    @XmlAttribute(name="stroke-color")
    private String strokeColor = "";

    /**
     * Vytvoření obdélníku - stejná barva pozadí/výplň
     * @param x
     * @param y
     * @param width
     * @param height
     * @param backgroundColor
     */
    public Rectangle(double x, double y, double width, double height, float strokeWidth, String backgroundColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.strokeWidth = strokeWidth;
        this.backgroundColor = backgroundColor;
        this.strokeColor = backgroundColor;
    }
    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param backgroundColor
     * @param strokeWidth
     * @param strokeColor
     */

    public Rectangle(double x, double y, double width, double height, String backgroundColor, float strokeWidth, String strokeColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
    }

    public Rectangle() {
    }

    @Override
    public void Draw(Graphics2D g) {
        g.setColor(Color.decode(this.strokeColor));
        g.setStroke(new BasicStroke(this.strokeWidth));
        g.drawRect((int)this.x, (int)this.y,(int)this.width, (int)this.height);
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }
}
