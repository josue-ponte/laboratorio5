package xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static xml.utils.Constants.XML_DOCUMENT;
import static xml.utils.Constants.XML_SCHEMA;

public class Application {

  public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

    XmlValidator xmlValidator = new XmlValidator();
    xmlValidator.isXmlWellFormed(XML_DOCUMENT); // bien formado solo necesita el documento xml
    xmlValidator.isXmlValidated(XML_DOCUMENT, XML_SCHEMA); // validado necesita el documento xml y el schema xml
  }
}
