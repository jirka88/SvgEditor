package svgeditor.table;

import svgeditor.components.TextArea;
import svgeditor.data.ElipseData;
import svgeditor.data.RectangleData;
import svgeditor.data.SegmentData;
import svgeditor.graphics.Elipse;
import svgeditor.graphics.Rectangle;
import svgeditor.graphics.Segment;
import svgeditor.panels.RenderPanel;
import svgeditor.render.Render;

import javax.swing.*;


import static javax.swing.JOptionPane.showMessageDialog;

public class TableAllGraphics extends JTable {
    private int selectedRow;
    private Render data;
    private RenderPanel graphicPanel;
    private JTable tableGraphics;
    private TextArea textArea;

    /**
     * @param data          data co má načíst
     * @param graphicPanel  panel, kde jsou dané obrazce
     * @param tableGraphics tabulka, která bude obsahovat data určitého obrazce
     */

    public TableAllGraphics(Render data, RenderPanel graphicPanel, JTable tableGraphics, TextArea textArea) {
        this.data = data;
        this.graphicPanel = graphicPanel;
        this.tableGraphics = tableGraphics;
        this.textArea = textArea;
        getSelectionModel().addListSelectionListener(e -> {
            selectedRow = getSelectedRow();
            if(selectedRow != -1) {
                switch (data.getList().get(selectedRow).getClass().getSimpleName().toLowerCase()) {
                    case "rectangle":
                        tableGraphics.setModel(new RectangleData((Rectangle) data.getList().get(selectedRow),textArea, data));
                        break;
                    case "elipse":
                        tableGraphics.setModel(new ElipseData((Elipse) data.getList().get(selectedRow), graphicPanel, textArea, data));
                        break;
                    case "segment":
                        tableGraphics.setModel(new SegmentData((Segment) data.getList().get(selectedRow), graphicPanel, textArea, data));
                        break;
                    default:
                        showMessageDialog(null, "Nastala chyba!");
                        break;
                }
            }
        });
    }
}
