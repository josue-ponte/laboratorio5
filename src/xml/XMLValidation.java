package xml;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class XMLValidation {
    public static void main(String[] args) {
        boolean flag1 = true;
        boolean flag2 = true;
        try {
            validate("dni1.xml", "dni1.xsd");
        } catch (SAXException ex) {
            flag2 = false;
        } catch (IOException ex) {
            flag1 = false;
        }
//        System.out.println("xml file esta bien formado: "+ flag1);
        System.out.println("xml file es valido: "+ flag2);
    }

    public static void validate(String xmlFile, String validationFile) throws SAXException, IOException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        ((schemaFactory.newSchema(new File(validationFile))).newValidator()).validate(new StreamSource(new File(xmlFile)));
    }
}
