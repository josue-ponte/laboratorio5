package xml;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Validator {

  public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setValidating(false);
    factory.setNamespaceAware(true);

    DocumentBuilder builder = factory.newDocumentBuilder();

    builder.setErrorHandler(new SimpleErrorHandler());
    // the "parse" method also validates XML, will throw an exception if misformatted
    Document document = builder.parse(new InputSource("dni1.xml"));
  }

  private static class SimpleErrorHandler implements ErrorHandler {
    public void warning(SAXParseException e) throws SAXException {
      System.out.println(e.getMessage());
    }

    public void error(SAXParseException e) throws SAXException {
      System.out.println(e.getMessage());
    }

    public void fatalError(SAXParseException e) throws SAXException {
      System.out.println(e.getMessage());
    }
  }
}
