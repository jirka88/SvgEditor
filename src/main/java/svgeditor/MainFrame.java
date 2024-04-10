package svgeditor;

import svgeditor.data.ElipseData;
import svgeditor.data.RectangleData;
import svgeditor.data.GraphicsData;
import svgeditor.graphics.Elipse;
import svgeditor.graphics.Rectangle;
import svgeditor.panels.RenderPanel;
import svgeditor.render.Render;
import svgeditor.table.TableAllGraphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static javax.swing.JOptionPane.showMessageDialog;

public class MainFrame extends JFrame {
    private final JPanel mainPanel;
    private final JPanel leftPanel;
    private final RenderPanel graphicPanel;
    private final TableAllGraphics tableAllGraphics;
    public MainFrame() {
        Render data = new Render();
        JTable tableGraphics = new JTable();
        graphicPanel = new RenderPanel(data);
        tableAllGraphics = new TableAllGraphics(data, graphicPanel, tableGraphics);
        tableAllGraphics.setModel(new GraphicsData(data));

        setTitle("SvgEditor");
        setVisible(true);
        setBackground(Color.white);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(2,1));
        leftPanel.add(new JScrollPane(tableAllGraphics));
        leftPanel.add(tableGraphics);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(graphicPanel, BorderLayout.CENTER);
        add(mainPanel);
    }
}
