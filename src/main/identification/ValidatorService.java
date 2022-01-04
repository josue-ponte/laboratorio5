package main.identification;

import static main.identification.utils.Constants.*;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import main.identification.exceptions.SimpleErrorHandler;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class ValidatorService {

  public void processXml() throws IOException, SAXException, ParserConfigurationException {
    if(isXmlWellFormed(XML_DOCUMENT)) {
      isXmlValidated(XML_DOCUMENT, XML_SCHEMA);
    }
  }

  private boolean isXmlValidated(String xmlDocument, String xmlSchema) throws SAXException, IOException {
    try {
      SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      ((schemaFactory.newSchema(new File(xmlSchema))).newValidator()).validate(new StreamSource(new File(xmlDocument)));
      System.out.println(MESSAGE_XML_DOCUMENT.concat(" válido"));
      return true;
    } catch (SAXException ex) {
      System.out.println(MESSAGE_XML_DOCUMENT.concat(" no válido"));
      return false;
    }
  }

  private boolean isXmlWellFormed(String xmlDocument) throws ParserConfigurationException, IOException, SAXException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setValidating(false); // soporte para validacion de xml
    factory.setNamespaceAware(true); // soporte para namespaces xml, por default en true

    try {
      DocumentBuilder builder = factory.newDocumentBuilder(); // leer documento xml
      builder.setErrorHandler(new SimpleErrorHandler()); // controlar excepcion
      builder.parse(new InputSource(xmlDocument));  // el método "parse" lanzará una excepción si está mal formado
      System.out.println(MESSAGE_XML_DOCUMENT.concat(" bien formado"));
      return true;
    } catch (SAXParseException ex) {
      System.out.println(MESSAGE_XML_DOCUMENT.concat(" mal formado"));
      return false;
    }
  }

}
