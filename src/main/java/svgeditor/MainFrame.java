package svgeditor;

import svgeditor.panels.RenderPanel;
import svgeditor.render.Render;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final JTabbedPane tabsPane;
    public MainFrame() {
        setTitle("SvgEditor");
        setVisible(true);
        setBackground(Color.white);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tabsPane = new JTabbedPane();
        add(tabsPane);

        Render render = new Render();
        tabsPane.addTab("Vykreslení", new RenderPanel(render));

    }
}
