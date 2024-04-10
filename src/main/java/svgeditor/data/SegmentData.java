package svgeditor.data;

import svgeditor.graphics.Segment;
import svgeditor.panels.RenderPanel;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;

import static javax.swing.JOptionPane.showMessageDialog;

public class SegmentData extends AbstractTableModel {
    Segment data;
    RenderPanel panel;

    public SegmentData(Segment data, RenderPanel panel) {
        this.data = data;
        this.panel = panel;
    }

    @Override
    public int getRowCount() {
        return 6;
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

        Field[] fields = Segment.class.getDeclaredFields();
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
                    return data.getX2();
                case 3:
                    return data.getY2();
                case 4:
                    return data.getWidth();
                case 5:
                    return data.getColor();
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
                        data.setX2(Double.parseDouble(aValue.toString()));
                        break;
                    case 3:
                        data.setY2(Double.parseDouble(aValue.toString()));
                        break;
                    case 4:
                        data.setWidth(Float.parseFloat(aValue.toString()));
                        break;
                    case 5:
                    data.setColor(aValue.toString());
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
