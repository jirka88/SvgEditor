package svgeditor.panels;

import svgeditor.components.TextArea;
import svgeditor.data.GraphicsData;
import svgeditor.data.PropertiesData;
import svgeditor.graphics.Elipse;
import svgeditor.graphics.Geometry;
import svgeditor.graphics.Rectangle;
import svgeditor.graphics.Segment;
import svgeditor.render.Render;
import svgeditor.table.TableAllGraphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class RenderPanel extends JPanel {
    private Render render;
    private Class<?> newElement = null;
    private PropertiesData propertiesData;
    private Point startPoint, endPoint;
    private TextArea textArea;
    int width, height;

    public RenderPanel(Render render, TextArea textArea) {
        this.render = render;
        this.textArea = textArea;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (newElement == null) {
                    return;
                }
                if (startPoint != null) {
                    if (newElement.getSimpleName().equals("Segment")) {
                        endPoint = e.getPoint();
                        Segment segment = new Segment(startPoint.x, startPoint.y, endPoint.x, endPoint.y, propertiesData.getWidth(), propertiesData.getColor());
                        render.addValueToList(segment);
                        startPoint = null;
                        if (propertiesData.isStillContinue()) {
                            startPoint = e.getPoint();
                        }
                        endPoint = null;
                        repaint();
                    } else {
                        startPoint = e.getPoint();
                    }
                    textArea.setData(render);
                    return;
                }
                startPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (newElement == null) {
                    return;
                }
                if (!newElement.getSimpleName().equals("Segment")) {
                    endPoint = e.getPoint();
                    width = Math.abs(endPoint.x - startPoint.x);
                    height = Math.abs(endPoint.y - startPoint.y);
                    if (width > 0 && height > 0) {
                        if (newElement.getSimpleName().equals("Rectangle")) {
                            Rectangle rectangle = new Rectangle(Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y), width, height, propertiesData.getWidth(), propertiesData.getColor());
                            render.addValueToList(rectangle);
                        } else {
                            Elipse elipse = new Elipse(Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y), width, height, propertiesData.getWidth(), propertiesData.getColor());
                            render.addValueToList(elipse);
                        }
                        repaint();
                    }
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (newElement != null && !newElement.getSimpleName().equals("Segment")) {
                    endPoint = e.getPoint();
                    repaint();
                }
            }
        });


    }

    public void setPaint(Class<?> d, PropertiesData propertiesData) {
        newElement = d;
        this.propertiesData = propertiesData;
    }

    public void setNewElement(Class<?> newElement) {
        this.newElement = newElement;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Geometry shape : render.getList()) {
            shape.Draw((Graphics2D) g);
        }
    }
}
