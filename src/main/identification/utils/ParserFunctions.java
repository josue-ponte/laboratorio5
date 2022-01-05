package main.identification.utils;

import static main.identification.utils.Constants.MESSAGE_XML_DOCUMENT;
import static main.identification.utils.Constants.XML_ROOT_DOCUMENT;

import main.identification.exceptions.SimpleErrorHandler;
import main.identification.model.UniversityCard;
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

  private final Stack<String> elements = new Stack<>();

  private final Stack<UniversityCard> objects = new Stack<>();

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

  public List<UniversityCard> toObject() {

    List<UniversityCard> universityCardList = new ArrayList<>();

    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser parser = factory.newSAXParser();

      InputStream is = new FileInputStream(XML_ROOT_DOCUMENT);
      parser.parse(is, new DefaultHandler() {

        @Override
        public void startElement(String uri,
                                 String localName,
                                 String qName,
                                 Attributes attributes) throws SAXException {
          elements.push(qName);
          if ("universityCard".equals(qName)) {
            UniversityCard record = new UniversityCard();
            objects.push(record);
            universityCardList.add(record);
          }
        }

        @Override
        public void endElement(String uri,
                               String localName,
                               String qName) throws SAXException {
          elements.pop();
        }

        @Override
        public void characters(char[] ch,
                               int start,
                               int length) throws SAXException {

          String value = new String(ch, start, length);
          if (value.length() == 0) {
            return;
          }

          if ("university".equals(currentElement())) {
            UniversityCard record = objects.peek();
            record.setUniversity(value);

          } else if ("code".equals(currentElement())) {
            UniversityCard record = objects.peek();
            record.setCode(value);

          } else if ("dni".equals(currentElement())) {
            UniversityCard record = objects.peek();
            record.setDni(value);

          } else if ("lastName".equals(currentElement())) {
            UniversityCard record = objects.peek();
            record.setLastName(value);

          } else if ("firstName".equals(currentElement())) {
            UniversityCard record = objects.peek();
            record.setFirstName(value);

          } else if ("faculty".equals(currentElement())) {
            UniversityCard record = objects.peek();
            record.setFaculty(value);

          } else if ("career".equals(currentElement())) {
            UniversityCard record = objects.peek();
            record.setCareer(value);

          } else if ("year".equals(currentElement())) {
            UniversityCard record = objects.peek();
            record.setYear(Integer.parseInt(value));

          } else if ("incomeYear".equals(currentElement())) {
            UniversityCard record = objects.peek();
            record.setIncomeYear(value);

          } else if ("expirationDate".equals(currentElement())) {
            UniversityCard record = objects.peek();
            record.setExpirationDate(value);

          }
        }

      });

    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }

    return universityCardList;
  }

  private String currentElement() {
    return elements.peek();
  }

}
