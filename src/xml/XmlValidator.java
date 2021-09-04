package xml;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import xml.exceptions.SimpleErrorHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class XmlValidator {

  public boolean isXmlValidated(String xmlDocument, String xmlSchema) throws SAXException, IOException {
    try {
      SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      ((schemaFactory.newSchema(new File(xmlSchema))).newValidator()).validate(new StreamSource(new File(xmlDocument)));
      System.out.println("Documento XML validado");
      return true;
    } catch (SAXException ex) {
      System.out.println("Documento XML no validado");
      return false;
    }
  }

  public boolean isXmlWellFormed(String xmlDocument) throws ParserConfigurationException, IOException, SAXException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setValidating(false);
    factory.setNamespaceAware(true);

    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      builder.setErrorHandler(new SimpleErrorHandler());
      Document document = builder.parse(new InputSource(xmlDocument));  // el método "parse" lanzará una excepción si está mal formado
      System.out.println("Documento XML bien formado");
      return true;
    } catch (SAXParseException ex) {
      System.out.println("Documento XML mal formado");
      return false;
    }
}
}
