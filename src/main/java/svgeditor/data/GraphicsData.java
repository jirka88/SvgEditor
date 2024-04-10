package svgeditor.data;

import svgeditor.render.Render;

import javax.swing.table.AbstractTableModel;

public class GraphicsData extends AbstractTableModel {
    private final int columnCount = 2;
    private Render render;
    private int index = 0;

    public GraphicsData(Render render) {
        this.render = render;
    }

    @Override
    public int getRowCount() {
        return render.getList().size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return ++rowIndex;
        }
        if (columnIndex == 1) {
            return render.getList().get(rowIndex).getClass().getSimpleName();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
