package svgeditor;

import svgeditor.Utils.JsonUtils;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class MainFrame extends JFrame {
    private final JPanel mainPanel;
    private final JPanel leftPanel;
    private final JToolBar bottomPanel;
    private final RenderPanel graphicPanel;
    private final TableAllGraphics tableAllGraphics;
    private final JToolBar grapgicTools;
    private final JButton makeSegmentBtn;
    private final JButton makeRectangleBtn;
    private final JButton makeElipseBtn;
    private final JButton disablePaintBtn;
    private final JButton exportBtn;
    private final JButton importBtn;
    private final JButton disabledXmlBtn;
    private final JComboBox<String> importSvg;
    private final JPanel toolbar;
    private final JPanel toolbarTable;
    private final JTable properties;
    private final JPanel saving;
    private PropertiesData propertiesData;
    private int selectedIndex = 0;
    private final JTable tableGraphics;
    private final String[] choices = {"do XML", "do JSON"};
    private Scanner sc = null;
    private String file = "";
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
        importBtn = new JButton("Import");
        importBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
                fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    file = "";
                    try {
                        sc = new Scanner(selectedFile);
                        while (sc.hasNextLine()) {
                            file += sc.nextLine();
                        }
                        data.setPaintList(XmlUtils.getImage(file));
                        XML.setText(XmlUtils.getXml(data));

                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        disabledXmlBtn = new JButton("Vypnout XML");
        disabledXmlBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!disabled) {
                    bottomPanel.setVisible(disabled);
                    disabledXmlBtn.setText("Zapnout XML");
                    disabled = true;
                } else {
                    bottomPanel.setVisible(disabled);
                    disabledXmlBtn.setText("Vypnout XML");
                    disabled = false;
                }
            }
        });
        saving = new JPanel();
        saving.setBackground(Color.WHITE);
        exportBtn = new JButton("Export");
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML OR JSON", "xml", "json");
        fileChooser.setFileFilter(filter);
        exportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userSelection = fileChooser.showSaveDialog(graphicPanel);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    String fileName = fileToSave.getName();
                    String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

                    if (!extension.equalsIgnoreCase("xml") && !extension.equalsIgnoreCase("json")) {
                        JOptionPane.showMessageDialog(null, "Špatný formát!");
                        return;
                    }
                    try {
                        FileWriter myWriter = new FileWriter(fileToSave.getAbsolutePath());
                        if (selectedIndex == 0) {
                            myWriter.write(XmlUtils.getXml(data));
                        } else {
                            myWriter.write(JsonUtils.getJson(data));
                        }
                        myWriter.close();
                        JOptionPane.showMessageDialog(null, "Uloženo!");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        importSvg = new JComboBox<String>(choices);
        importSvg.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                selectedIndex = importSvg.getSelectedIndex();
            }
        });
        saving.add(disabledXmlBtn);
        saving.add(exportBtn);
        saving.add(importSvg);
        saving.add(importBtn);

        //Nástroje

        toolbarTable = new JPanel();
        toolbarTable.setLayout(new FlowLayout());
        properties = new JTable();
        toolbarTable.add(properties);
        grapgicTools = new JToolBar();
        grapgicTools.setBorder(new TitledBorder(new EtchedBorder(), "Nástroje"));
        grapgicTools.setOrientation(SwingConstants.VERTICAL);
        grapgicTools.setPreferredSize(new Dimension(200, 100));
        graphicPanel = new RenderPanel(data, XML);

        tableAllGraphics = new TableAllGraphics(data, graphicPanel, tableGraphics, XML);
        tableAllGraphics.setModel(new GraphicsData(data));

        disablePaintBtn = new JButton("Zruš výběr");
        disablePaintBtn.setVisible(false);
        makeSegmentBtn = new JButton("Úsečka");
        makeSegmentBtn.addActionListener(e -> {
            setProperties(Segment.class);
        });

        makeRectangleBtn = new JButton("Čtverec");
        makeRectangleBtn.addActionListener(e -> {
            setProperties(Rectangle.class);
        });

        makeElipseBtn = new JButton("Elipsa");
        makeElipseBtn.addActionListener(e -> {
            setProperties(Elipse.class);
        });
        disablePaintBtn.addActionListener(e -> {
            graphicPanel.setNewElement(null);
            properties.setModel(new DefaultTableModel());
            disablePaintBtn.setVisible(false);
        });

        toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(5, 1));
        grapgicTools.setOrientation(SwingConstants.VERTICAL);
        grapgicTools.setPreferredSize(new Dimension(200, 100));

        toolbar.add(makeSegmentBtn);
        toolbar.add(makeRectangleBtn);
        toolbar.add(makeElipseBtn);
        toolbar.add(disablePaintBtn);

        grapgicTools.add(toolbar, BorderLayout.CENTER);
        grapgicTools.add(toolbarTable, BorderLayout.SOUTH);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(2, 1));
        leftPanel.add(new JScrollPane(tableAllGraphics));
        leftPanel.add(tableGraphics);

        bottomPanel = new JToolBar();
        bottomPanel.setBorder(new TitledBorder(new EtchedBorder(), "XML"));

        XML.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setXml(data, XML);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setXml(data, XML);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                setXml(data, XML);
            }
        });
        bottomPanel.setLayout(new GridLayout(1, 1));
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
        if (element.getSimpleName().equals("Segment")) {
            propertiesData = new PropertiesData(new Segment());
        }
        properties.setModel(propertiesData);
        graphicPanel.setPaint(element, propertiesData);
        disablePaintBtn.setVisible(true);
    }

    private void setXml(Render data, TextArea XML) {
        if(!XML.getText().equals("")) {
            try {
                tableGraphics.setModel(new DefaultTableModel());
                Render image = XmlUtils.getImage(XML.getText());
                data.setPaintList(image);
                tableAllGraphics.setModel(new GraphicsData(data));
                graphicPanel.repaint();
            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "Nastala chyba při editaci!");
            }
        }
    }
}
