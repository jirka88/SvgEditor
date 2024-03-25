package svgeditor.graphics;

import java.awt.*;

public class Section implements Geometry{
    private double x =0;
    private double y =0;
    private double x2 = 0;
    private double y2 = 0;
    private String color = "";

    public Section(double x, double y, double x2, double y2, String color) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    @Override
    public void Draw(Graphics2D g) {
        g.drawLine((int)x,(int)y,(int) x2,(int) y2);
    }
}
