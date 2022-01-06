package main.identification.utils;

import static main.identification.utils.Constants.MESSAGE_XML_DOCUMENT;
import static main.identification.utils.Constants.XML_ROOT_DOCUMENT;

import main.identification.exceptions.SimpleErrorHandler;
import main.identification.model.*;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParserFunctions {

  public boolean isXmlValidated(String xmlDocument, String xmlSchema) throws IOException {
    try {
      SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      ((schemaFactory.newSchema(new File(xmlSchema))).newValidator()).validate(new StreamSource(new File(xmlDocument)));
      System.out.println(MESSAGE_XML_DOCUMENT.concat(" válido"));
      return true;

    } catch (SAXException ex) {
      System.out.println(MESSAGE_XML_DOCUMENT.concat(" no válido"));
      return false;
    }
  }

  public boolean isXmlWellFormed(String xmlDocument) throws ParserConfigurationException, IOException, SAXException {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setValidating(false); // soporte para validacion de xml
    factory.setNamespaceAware(true); // soporte para namespaces xml, por default en true

    try {
      DocumentBuilder builder = factory.newDocumentBuilder(); // leer documento xml
      builder.setErrorHandler(new SimpleErrorHandler()); // controlar excepcion
      builder.parse(new InputSource(xmlDocument));  // el método "parse" lanzará una excepción si está mal formado
      System.out.println(MESSAGE_XML_DOCUMENT.concat(" bien formado"));
      return true;

    } catch (SAXParseException ex) {
      System.out.println(MESSAGE_XML_DOCUMENT.concat(" mal formado"));
      return false;
    }
  }

  public List<UniversityCard> toObject(String elementName, String documentRoot) {

    List<UniversityCard> elementList = new ArrayList<>();
    Stack<String> tagStack = new Stack<>();
    Stack<UniversityCard> elementStack = new Stack<>();

    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser parser = factory.newSAXParser();

      InputStream is = new FileInputStream(XML_ROOT_DOCUMENT);
      parser.parse(is, new DefaultHandler() {

        @Override
        public void startElement(String uri, String localName, String tagName, Attributes attributes) {
          tagStack.push(tagName);
          if (elementName.equals(tagName)) {

            UniversityCard universityCard = new UniversityCard();
            initializeUniversityCard(universityCard);

            elementStack.push(universityCard);
            elementList.add(universityCard);
          }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
          tagStack.pop();
        }

        @Override
        public void characters(char[] ch, int start, int length) {

          String value = new String(ch, start, length);
          if (value.length() == 0) {
            return;
          }

          buildUniversityCard(elementStack, tagStack, value);
        }

      });

    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }

    return elementList;
  }

  private void buildUniversityCard(Stack<UniversityCard> elementStack,
                                   Stack<String> tagStack,
                                   String value) {

    if ("year".equals(tagStack.peek())) {
      UniversityCard actualUniversityCard = elementStack.peek();
      actualUniversityCard.setYear(Integer.parseInt(value));

    } else if ("expirationDate".equals(tagStack.peek())) {
      UniversityCard actualUniversityCard = elementStack.peek();
      actualUniversityCard.setExpirationDate(value);

    } else if ("firstName".equals(tagStack.peek())) {
      UniversityCard actualUniversityCard = elementStack.peek();
      Student actualStudent = actualUniversityCard.getStudent();
      actualStudent.setFirstName(value);

    } else if ("lastName".equals(tagStack.peek())) {
      UniversityCard actualUniversityCard = elementStack.peek();
      Student actualStudent = actualUniversityCard.getStudent();
      actualStudent.setLastName(value);

    } else if ("dni".equals(tagStack.peek())) {
      UniversityCard actualUniversityCard = elementStack.peek();
      Student actualStudent = actualUniversityCard.getStudent();
      actualStudent.setDni(value);

    } else if ("code".equals(tagStack.peek())) {
      UniversityCard actualUniversityCard = elementStack.peek();
      Student actualStudent = actualUniversityCard.getStudent();
      actualStudent.setCode(value);

    } else if ("incomeYear".equals(tagStack.peek())) {
      UniversityCard actualUniversityCard = elementStack.peek();
      Student actualStudent = actualUniversityCard.getStudent();
      actualStudent.setIncomeYear(value);

    } else if ("careerName".equals(tagStack.peek())) {
      UniversityCard actualUniversityCard = elementStack.peek();
      Student actualStudent = actualUniversityCard.getStudent();
      Career actualCareer = actualStudent.getCareer();
      actualCareer.setCareerName(value);

    } else if ("facultyName".equals(tagStack.peek())) {
      UniversityCard actualUniversityCard = elementStack.peek();
      Student actualStudent = actualUniversityCard.getStudent();
      Career actualCareer = actualStudent.getCareer();
      Faculty actualFaculty = actualCareer.getFaculty();
      actualFaculty.setFacultyName(value);

    } else if ("universityName".equals(tagStack.peek())) {
      UniversityCard actualUniversityCard = elementStack.peek();
      Student actualStudent = actualUniversityCard.getStudent();
      Career actualCareer = actualStudent.getCareer();
      Faculty actualFaculty = actualCareer.getFaculty();
      University actualUniversity = actualFaculty.getUniversity();
      actualUniversity.setUniversityName(value);

    }
  }

  private void initializeUniversityCard(UniversityCard universityCard) {
    University university = new University();

    Faculty faculty = new Faculty();
    faculty.setUniversity(university);

    Career career = new Career();
    career.setFaculty(faculty);

    Student student = new Student();
    student.setCareer(career);

    universityCard.setStudent(student);
  }

}
