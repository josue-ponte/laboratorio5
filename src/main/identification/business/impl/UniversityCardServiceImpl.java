package main.identification.business.impl;

import main.identification.business.UniversityCardService;
import main.identification.utils.ParserFunctions;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static main.identification.utils.Constants.XML_DOCUMENT;
import static main.identification.utils.Constants.XML_SCHEMA;

public class UniversityCardServiceImpl implements UniversityCardService {

  private final ParserFunctions parserFunctions;

  public UniversityCardServiceImpl() {
    parserFunctions = new ParserFunctions();
  }

  @Override
  public void processXmlDocument() throws IOException, SAXException, ParserConfigurationException {
      if(parserFunctions.isXmlWellFormed(XML_DOCUMENT)) {
        parserFunctions.isXmlValidated(XML_DOCUMENT, XML_SCHEMA);
      }
  }

  @Override
  public long countStudentsByProfessionalSchool(String professionalSchool) {
    return parserFunctions.toObject()
        .stream()
        .filter(universityCard -> universityCard.getCareer().equals(professionalSchool))
        .count();
  }
}
