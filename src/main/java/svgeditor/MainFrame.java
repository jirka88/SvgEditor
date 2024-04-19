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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    private final JButton importBtn;
    private final JButton disabledXml;
    private final JComboBox<String> importSvg;
    private final JPanel toolbar;
    private final JPanel toolbarTable;
    private final JTable properties;
    private final JPanel saving;
    private PropertiesData propertiesData;
    private int selectedIndex = 0;
    private final JTable tableGraphics;
    private final String[] choices = {"do XML", "do JSON"};
    private boolean disabled = false;
    public MainFrame() {
        //SET WINDOW
        setTitle("SvgEditor");
        setVisible(true);
        setBackground(Color.white);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Render data = new Render();
        tableGraphics = new JTable();
        TextArea XML = new TextArea(data);

        //IMPORT - EXPORT a DISABLED XML
        disabledXml = new JButton("Vypnout XML");
        disabledXml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!disabled) {
                    bottomPanel.setVisible(disabled);
                    disabledXml.setText("Zapnout XML");
                    disabled = true;
                }
                else {
                    bottomPanel.setVisible(disabled);
                    disabledXml.setText("Vypnout XML");
                    disabled = false;
                }
            }
        });
        saving = new JPanel();
        saving.setBackground(Color.WHITE);
        importBtn = new JButton("Import");
        importBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File outputFolder = new File("output");
                if (!outputFolder.exists()) {
                    outputFolder.mkdirs();
                }
                if(selectedIndex == 0) {
                    try {
                        FileWriter myWriter = new FileWriter("output/data.xml");
                        myWriter.write(XmlUtils.getXml(data));
                        myWriter.close();
                        JOptionPane.showMessageDialog(null,"Uloženo!");
                    } catch (IOException ex) {
                           throw new RuntimeException(ex);
                    }
                }
                else {

                }
            }
        });
        importSvg = new JComboBox<String>(choices);
        importSvg.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                selectedIndex = importSvg.getSelectedIndex();
            }
        });
        saving.add(disabledXml);
        saving.add(importBtn);
        saving.add(importSvg);

        toolbarTable = new JPanel();
        toolbarTable.setLayout(new FlowLayout());
        properties = new JTable();
        toolbarTable.add(properties);
        grapgicTools = new JToolBar();
        grapgicTools.setBorder(new TitledBorder(new EtchedBorder(), "Nástroje"));
        grapgicTools.setOrientation(SwingConstants.VERTICAL);
        grapgicTools.setPreferredSize(new Dimension(200,  100));
        graphicPanel = new RenderPanel(data, XML);

        tableAllGraphics = new TableAllGraphics(data, graphicPanel, tableGraphics, XML);
        tableAllGraphics.setModel(new GraphicsData(data));

        disablePaint = new JButton("Zruš výběr");
        disablePaint.setVisible(false);
        makeSegment = new JButton("Úsečka");
        makeSegment.addActionListener(e -> {
            setProperties(Segment.class);
        });

        makeRectangle = new JButton("Čtverec");
        makeRectangle.addActionListener(e -> {
            setProperties(Rectangle.class);
        });

        makeElipse = new JButton("Elipsa");
        makeElipse.addActionListener(e -> {
            setProperties(Elipse.class);
        });
        disablePaint.addActionListener(e -> {
            graphicPanel.setNewElement(null);
            properties.setModel(new DefaultTableModel());
            disablePaint.setVisible(false);
        });

        toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(5,1));
        grapgicTools.setOrientation(SwingConstants.VERTICAL);
        grapgicTools.setPreferredSize(new Dimension(200,  100));

        toolbar.add(makeSegment);
        toolbar.add(makeRectangle);
        toolbar.add(makeElipse);
        toolbar.add(disablePaint);

        grapgicTools.add(toolbar, BorderLayout.CENTER);
        grapgicTools.add(toolbarTable, BorderLayout.SOUTH);

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
                tableGraphics.setModel(new DefaultTableModel());
                Render d = XmlUtils.getImage(XML.getText());
                data.setPaintList(d);
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

        //ADDING PANELS TO MAINPANEL
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(graphicPanel, BorderLayout.CENTER);
        mainPanel.add(grapgicTools, BorderLayout.EAST);
        mainPanel.add(saving, BorderLayout.NORTH);
        add(mainPanel);
    }
    private void setProperties(Class<?> element) {
        tableGraphics.setModel(new DefaultTableModel());
        propertiesData = new PropertiesData();
        if(element.getSimpleName().equals("Segment")) {
            propertiesData = new PropertiesData(new Segment());
        }
        properties.setModel(propertiesData);
        graphicPanel.setPaint(element, propertiesData, tableAllGraphics);
        disablePaint.setVisible(true);
    }
}
