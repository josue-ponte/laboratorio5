package main.identification.business;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface UniversityCardService {

  void processXmlDocument() throws IOException, SAXException, ParserConfigurationException;

  long countStudentsByProfessionalSchool(String professionalSchool) throws IOException, SAXException, ParserConfigurationException;

}
