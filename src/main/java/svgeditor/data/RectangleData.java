package svgeditor.data;
import svgeditor.Utils.XmlUtils;
import svgeditor.components.TextArea;
import svgeditor.graphics.Rectangle;
import svgeditor.panels.RenderPanel;
import svgeditor.render.Render;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import static javax.swing.JOptionPane.showMessageDialog;

public class RectangleData extends AbstractTableModel {
    private Rectangle data;
    private RenderPanel panel;
    private final String[] columnNames = {"Index", "Tvar"};
    private TextArea textArea;
    private Render dataList;

    /**
     * Vytvoří tabulka s daty
     * @param data
     * @param panel
     */
    public RectangleData(Rectangle data, RenderPanel panel, TextArea textArea, Render dataList) {
        this.data = data;
        this.panel = panel;
        this.textArea = textArea;
        this.dataList = dataList;
    }

    @Override
    public int getRowCount() {
        return 7;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Field[] fields = Rectangle.class.getDeclaredFields();
        if(columnIndex == 0)
        {
            return fields[rowIndex].getName();
        }
        else {
                switch (rowIndex) {
                    case 0:
                        return data.getX();
                    case 1:
                        return data.getY();
                    case 2:
                        return data.getWidth();
                    case 3:
                        return data.getHeight();
                    case 4:
                        return data.getBackgroundColor();
                    case 5:
                        return data.getStrokeWidth();
                    case 6:
                        return data.getStrokeColor();
                    default:
                        throw new IllegalArgumentException("Neplatný index řádku: " + rowIndex);
                }
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        if(columnIndex == 1) {
            try{
                switch (rowIndex) {
                    case 0:
                        data.setX(Double.parseDouble(aValue.toString()));
                        break;
                    case 1:
                        data.setY(Double.parseDouble(aValue.toString()));
                        break;
                    case 2:
                        data.setWidth(Double.parseDouble(aValue.toString()));
                        break;
                    case 3:
                        data.setHeight(Double.parseDouble(aValue.toString()));
                        break;
                    case 4:
                        data.setBackgroundColor(aValue.toString());
                        break;
                    case 5:
                        data.setStrokeWidth(Float.parseFloat(aValue.toString()));
                        break;
                    case 6:
                        data.setStrokeColor(aValue.toString());
                        break;
                    default:
                        throw new IllegalArgumentException("Neplatný index řádku: " + rowIndex);
                }

            }
            catch (Exception e) {
                showMessageDialog(null, "Špatně zadaný vstup!");
            }
            XmlUtils.isEdited = false;
            textArea.setData(dataList);
            panel.repaint();
        }
    }
}
