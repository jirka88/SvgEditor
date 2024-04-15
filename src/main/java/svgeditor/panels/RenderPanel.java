package svgeditor.panels;

import svgeditor.graphics.Geometry;
import svgeditor.render.Render;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderPanel extends JPanel {
    Render render;
    public RenderPanel(Render render) {
        this.render = render;
    }

    public void setRender(Render render) {
        this.render = render;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Geometry shape : render.getList()) {
            shape.Draw((Graphics2D) g);
        }
    }
}
