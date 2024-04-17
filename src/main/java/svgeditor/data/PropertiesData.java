package svgeditor.data;

import svgeditor.graphics.Geometry;
import svgeditor.graphics.Segment;

import javax.swing.table.AbstractTableModel;

public class PropertiesData extends AbstractTableModel {
    private Segment segment;
    private String color = "#000000";
    private float width = 1;
    private boolean stillContinue = false;

    public PropertiesData(Segment geometry) {
        this.segment = geometry;
    }

    public PropertiesData() {
    }

    @Override
    public int getRowCount() {
        if (segment != null) {
            return 3;
        }
        return 2;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            switch (rowIndex) {
                case 0:
                    return "Barva";
                case 1:
                    return "Width";
            }
            if (rowIndex == 2 && segment != null) {
                return "Pokračovat?";
            }
        }
        if (columnIndex == 1) {
            switch (rowIndex) {
                case 0:
                    return color;
                case 1:
                    return width;
            }
            if (rowIndex == 2 && segment != null) {
                return Boolean.valueOf(stillContinue);
            }
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            switch (rowIndex) {
                case 0:
                    color = aValue.toString();
                    break;
                case 1:
                    width = Float.parseFloat(aValue.toString());
                    break;
                case 2:
                    stillContinue = Boolean.parseBoolean(aValue.toString());
                    break;
            }
        }
    }

    public String getColor() {
        return color;
    }

    public float getWidth() {
        return width;
    }

    public boolean isStillContinue() {
        return stillContinue;
    }
}
