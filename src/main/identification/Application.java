package main.identification;

import main.identification.business.UniversityCardService;
import main.identification.business.impl.UniversityCardServiceImpl;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Application {

  public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

    UniversityCardService universityCardService = new UniversityCardServiceImpl();

    universityCardService.processXmlDocument();

    System.out.println("Estudiantes de Ingeniería de Sistemas: "
        .concat(String.valueOf(universityCardService.countStudentsByProfessionalSchool("INGENIERIA DE SISTEMAS"))));

    System.out.println("Estudiantes de Ingeniería de Software: "
        .concat(String.valueOf(universityCardService.countStudentsByProfessionalSchool("INGENIERIA DE SOFTWARE"))));

  }
}
