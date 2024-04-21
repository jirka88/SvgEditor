package svgeditor.Utils;

import svgeditor.render.Render;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlUtils {
    public static Boolean isEdited = true;
    public static String getXml(Render data) {
        try {
            JAXBContext context = JAXBContext.newInstance(Render.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            marshaller.marshal(data, sw);
            return sw.toString();
        }
        catch(JAXBException e) {
            throw new RuntimeException();
        }
    }
    public static Render getImage(String xml)
    {
        try
        {
            JAXBContext ctx = JAXBContext.newInstance(Render.class);
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            StringReader sr = new StringReader(xml);
            return (Render)unmarshaller.unmarshal(sr);

        } catch (JAXBException e)
        {
            throw new RuntimeException(e);
        }
    }
}
