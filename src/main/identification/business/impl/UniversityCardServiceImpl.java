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
        parserFunctions.isXmlValidated(XML_DOCUMENT, XML_SCHEMA);
      }
  }

  @Override
  public long countStudentsByProfessionalSchool(String professionalSchool) {
    return parserFunctions.toObject("universityCard", XML_ROOT_DOCUMENT)
        .stream()
        .filter(universityCard -> universityCard
            .getStudent()
            .getCareer()
            .getCareerName().equals(professionalSchool))
        .count();
  }
}
