package im.prox.mapper.example;

import im.prox.mapper.exception.MappingException;
import im.prox.mapper.exception.MissingRequiredFieldException;
import im.prox.mapper.mapping.FieldDescriptor;
import im.prox.mapper.mapping.MappedObject;
import im.prox.mapper.mapping.MappingFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.PrintStream;

public class Example {

	public static void main(String[] args) {

		Person person = new Person();

		Document doc;
		try {
			doc = DocumentHelper.parseText(SAMPLE_XML);
		} catch(DocumentException e) {
			e.printStackTrace();
			return;
		}

		Element base = doc.getRootElement();

		try {
			person = MappingFactory.map(person, base);
		} catch(MappingException e) {
			e.printStackTrace();
		}

		System.out.println(person.toString());

	}

	public static String SAMPLE_XML =
	"<person age=\"30\">\n" +
	"  <name>\n" +
	"    <first>John</first>\n" +
	"    <last>Doe</last>\n" +
	"  </name>\n" +
	"</person>";

}
