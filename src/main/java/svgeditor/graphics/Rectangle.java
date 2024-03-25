package svgeditor.graphics;

import java.awt.*;

public class Rectangle implements Geometry{
    private double x = 0;
    private double y = 0;
    private double width = 0;
    private double height = 0;
    private String backgroundColor = "";
    private float strokeWidth = 0;

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

    @Override
    public void Draw(Graphics2D g) {
        g.drawRect((int)this.x, (int)this.y,(int)this.width, (int)this.height);
        g.setColor(Color.decode(this.strokeColor));
        g.setStroke(new BasicStroke(this.strokeWidth));
    }
}
