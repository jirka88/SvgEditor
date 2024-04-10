package svgeditor.data;

import svgeditor.graphics.Rectangle;
import svgeditor.panels.RenderPanel;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;

import static javax.swing.JOptionPane.showMessageDialog;

public class RectangleData extends AbstractTableModel {
    Rectangle data;
    RenderPanel panel;

    /**
     * Vytvoří tabulka s daty
     * @param data
     * @param panel
     */
    public RectangleData(Rectangle data, RenderPanel panel) {
        int count = 0;
        this.data = data;
        this.panel = panel;
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
            panel.repaint();
        }
    }
}