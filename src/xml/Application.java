package xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static xml.utils.Constants.XML_DOCUMENT;
import static xml.utils.Constants.XML_SCHEMA;

public class Application {

  public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

    XmlValidator xmlValidator = new XmlValidator();
    if(xmlValidator.isXmlWellFormed(XML_DOCUMENT)) {
      xmlValidator.isXmlValidated(XML_DOCUMENT, XML_SCHEMA);
    }
  }
}
