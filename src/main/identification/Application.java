package main.identification;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Application {

  public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

    ValidatorService validatorService = new ValidatorService();
    validatorService.processXml();

    CountStudentsService service = new CountStudentsService();
    service.run();
  }
}
