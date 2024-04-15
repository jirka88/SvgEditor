package svgeditor;
import svgeditor.Utils.XmlUtils;
import svgeditor.components.TextArea;
import svgeditor.data.GraphicsData;
import svgeditor.panels.RenderPanel;
import svgeditor.render.Render;
import svgeditor.table.TableAllGraphics;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MainFrame extends JFrame {
    private final JPanel mainPanel;
    private final JPanel leftPanel;
    private final JPanel rightPanel;
    private final RenderPanel graphicPanel;
    private final TableAllGraphics tableAllGraphics;
    public MainFrame() {
        Render data = new Render();
        JTable tableGraphics = new JTable();
        TextArea XML = new TextArea(data);

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

        rightPanel = new JPanel();

        XML.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Render d = XmlUtils.getImage(XML.getText());
                graphicPanel.setRender(d);
                graphicPanel.repaint();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
//                graphicPanel.setRender(XmlUtils.getImage(XML.getText()));
//                graphicPanel.repaint();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        rightPanel.setLayout(new GridLayout(1,1));
        rightPanel.add(XML);

        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(graphicPanel, BorderLayout.CENTER);

        add(mainPanel);
    }
}
