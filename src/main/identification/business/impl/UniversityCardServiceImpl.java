package main.identification.business.impl;

import static main.identification.utils.Constants.*;

import main.identification.business.UniversityCardService;
import main.identification.utils.ParserFunctions;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class UniversityCardServiceImpl implements UniversityCardService {

  private final ParserFunctions parserFunctions;

  public UniversityCardServiceImpl() {
    parserFunctions = new ParserFunctions();
  }

  @Override
  public void processXmlDocument() throws IOException, SAXException, ParserConfigurationException {


      if(parserFunctions.isXmlWellFormed(XML_DOCUMENT)) {
        System.out.println(MESSAGE_XML_DOCUMENT.concat(" bien formado"));
        if (parserFunctions.isXmlValidated(XML_DOCUMENT, XML_SCHEMA)) {
          System.out.println(MESSAGE_XML_DOCUMENT.concat(" válido"));
        } else {
          System.out.println(MESSAGE_XML_DOCUMENT.concat(" no válido"));
        }
      } else {
        System.out.println(MESSAGE_XML_DOCUMENT.concat(" mal formado"));
      }
  }

  @Override
  public long countStudentsByProfessionalSchool(String professionalSchool)
      throws IOException, SAXException, ParserConfigurationException {
    if (parserFunctions.isXmlWellFormed(XML_DOCUMENT) && parserFunctions.isXmlValidated(XML_DOCUMENT, XML_SCHEMA)) {
      return parserFunctions.toObject("universityCard", XML_DOCUMENT_LIST)
          .stream()
          .filter(universityCard -> universityCard
              .getStudent()
              .getCareer()
              .getCareerName().equals(professionalSchool))
          .count();
    } else {
      return 0L;
    }
  }
}
