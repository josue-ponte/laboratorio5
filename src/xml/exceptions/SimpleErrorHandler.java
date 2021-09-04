package xml.exceptions;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler {

  public void warning(SAXParseException e) throws SAXException {
    System.out.println("WARNING " + e.getMessage());
  }

  public void error(SAXParseException e) throws SAXException {
    System.out.println("ERROR" + e.getMessage());
  }

  public void fatalError(SAXParseException e) throws SAXException {
    System.out.println("FATAL ERROR " + e.getMessage());
  }
}
