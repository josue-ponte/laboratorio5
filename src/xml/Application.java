package xml;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Application {

  public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

    XmlValidator xmlValidator = new XmlValidator();
    xmlValidator.processXml();
  }
}
