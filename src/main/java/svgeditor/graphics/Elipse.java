package svgeditor.graphics;

import java.awt.*;

public class Elipse implements Geometry{
    private double x = 0;
    private double y = 0;
    private double width = 0;
    private double height = 0;
    private String backgroundColor = "";
    private float strokeWidth = 0;
    private String strokeColor = "";

    /**
     * Vytvoření elipsy - stejná barva pozadí/výplň
     * @param x
     * @param y
     * @param width
     * @param height
     * @param strokeWidth
     * @param backgroundColor
     */
    public Elipse(double x, double y, double width, double height, float strokeWidth, String backgroundColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.strokeWidth = strokeWidth;
        this.backgroundColor = backgroundColor;
        this.strokeColor = backgroundColor;
    }

    public Elipse(double x, double y, double width, double height, String backgroundColor, float strokeWidth, String strokeColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.strokeWidth = strokeWidth;
        this.backgroundColor = backgroundColor;
        this.strokeColor = strokeColor;
    }

    @Override
    public void Draw(Graphics2D g) {
        g.setColor(Color.decode(this.strokeColor));
        g.setBackground(Color.decode(this.backgroundColor));
        g.setStroke(new BasicStroke(this.strokeWidth));
        g.drawOval((int)this.x, (int)this.y,(int)this.width, (int)this.height);
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

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public String getStrokeColor() {
        return strokeColor;
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
