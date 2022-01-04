package main.identification;

import main.identification.model.UniversityCard;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static main.identification.utils.Constants.XML_DOCUMENT;

public class CountStudentsService {

  private final List<UniversityCard> records = new ArrayList<>();

  private final Stack<String> elements = new Stack<>();
  private final Stack<UniversityCard> objects = new Stack<>();

  public void run() {
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser parser = factory.newSAXParser();

      InputStream is = getClass().getResourceAsStream(XML_DOCUMENT);

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
            records.add(record);
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

          } else if ("semester".equals(currentElement())) {
            UniversityCard record = objects.peek();
            record.setSemester(value);

          } else if ("expirationDate".equals(currentElement())) {
            UniversityCard record = objects.peek();
            record.setExpirationDate(value);

          }
        }

      });

    } catch (ParserConfigurationException e) {
      e.printStackTrace();

    } catch (SAXException | IOException e) {
      e.printStackTrace();

    }

    for(UniversityCard universityCard: records) {
      System.out.println("record: " + universityCard);
    }
  }

  private String currentElement() {
    return elements.peek();
  }
}
