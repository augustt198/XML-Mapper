package im.prox.mapper.example;

import im.prox.mapper.mapping.FieldDescriptor;
import im.prox.mapper.mapping.MappedObject;

import java.io.PrintStream;

public class Example {

	public static void main(String[] args) {

		PrintStream log = System.out;

		Person person = new Person();

		MappedObject obj = new MappedObject(person);

		log.println("# of field descriptors: " + obj.getFieldDescriptors().size());

		for(FieldDescriptor fd : obj.getFieldDescriptors()) {
			log.println(fd.toString());
		}

	}

}
