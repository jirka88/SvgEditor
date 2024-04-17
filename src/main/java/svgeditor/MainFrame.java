package svgeditor;
import svgeditor.Utils.XmlUtils;
import svgeditor.components.TextArea;
import svgeditor.data.GraphicsData;
import svgeditor.data.PropertiesData;
import svgeditor.graphics.Elipse;
import svgeditor.graphics.Rectangle;
import svgeditor.graphics.Segment;
import svgeditor.panels.RenderPanel;
import svgeditor.render.Render;
import svgeditor.table.TableAllGraphics;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MainFrame extends JFrame {
    private final JPanel mainPanel;
    private final JPanel leftPanel;
    private final JToolBar bottomPanel;
    private final RenderPanel graphicPanel;
    private final TableAllGraphics tableAllGraphics;
    private final JToolBar grapgicTools;
    private final JButton makeSegment;
    private final JButton makeRectangle;
    private final JButton makeElipse;
    private final JButton disablePaint;
    private final JPanel toolbar;
    private final JPanel toolbarTable;
    private final JTable properties;
    private PropertiesData propertiesData;
    public MainFrame() {
        Render data = new Render();
        JTable tableGraphics = new JTable();
        TextArea XML = new TextArea(data);

        toolbarTable = new JPanel();
        toolbarTable.setLayout(new FlowLayout());
        properties = new JTable();
        toolbarTable.add(properties);
        grapgicTools = new JToolBar();
        grapgicTools.setOrientation(SwingConstants.VERTICAL);
        grapgicTools.setPreferredSize(new Dimension(200,  100));
        graphicPanel = new RenderPanel(data, XML);

        disablePaint = new JButton("Zruš výběr");
        disablePaint.setVisible(false);
        makeSegment = new JButton("Úsečka");
        makeSegment.addActionListener(e -> {
            propertiesData = new PropertiesData(new Segment());
            properties.setModel(propertiesData);
            graphicPanel.setPaint(Segment.class, propertiesData);
            disablePaint.setVisible(true);
        });

        makeRectangle = new JButton("Čtverec");
        makeRectangle.addActionListener(e -> {
            propertiesData = new PropertiesData();
            properties.setModel(propertiesData);
            graphicPanel.setPaint(Rectangle.class, propertiesData);
            disablePaint.setVisible(true);
        });

        makeElipse = new JButton("Elipsa");
        makeElipse.addActionListener(e -> {
            propertiesData = new PropertiesData();
            properties.setModel(propertiesData);
            graphicPanel.setPaint(Elipse.class, propertiesData);
            disablePaint.setVisible(true);
        });
        disablePaint.addActionListener(e -> {
            graphicPanel.setNewElement(null);
            properties.setVisible(false);
            disablePaint.setVisible(false);
        });

        toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(5,1));

        toolbar.add(makeSegment);
        toolbar.add(makeRectangle);
        toolbar.add(makeElipse);
        toolbar.add(disablePaint);

        grapgicTools.add(toolbar, BorderLayout.CENTER);
        grapgicTools.add(toolbarTable, BorderLayout.SOUTH);


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

        bottomPanel = new JToolBar();
        bottomPanel.setBorder(new TitledBorder(new EtchedBorder(), "XML"));

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
        bottomPanel.setLayout(new GridLayout(1,1));
        bottomPanel.add(new JScrollPane(XML));


        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(graphicPanel, BorderLayout.CENTER);
        mainPanel.add(grapgicTools, BorderLayout.EAST);
        add(mainPanel);
    }
}
