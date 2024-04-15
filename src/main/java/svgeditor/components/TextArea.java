package svgeditor.components;
import svgeditor.Utils.XmlUtils;
import svgeditor.render.Render;
import javax.swing.*;

public class TextArea extends JTextArea {
    Render data;
    public TextArea(Render data) {
        this.data = data;
        setRows(data.getList().size() + 1);
        setColumns(50);
        append(XmlUtils.getXml(this.data));
    }
    public void setData(Render data) {
        append(XmlUtils.getXml(this.data));
    }

}
