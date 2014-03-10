package im.prox.mapper.mapping;

import im.prox.mapper.exception.MappingException;
import im.prox.mapper.exception.MissingRequiredFieldException;
import im.prox.mapper.interpret.Interpreter;
import im.prox.mapper.utils.ElementUtils;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MappingFactory {

	private static List<Interpreter> interpreters = new ArrayList<>();

	public static Mappable map(Mappable mappable, Element element) throws MappingException {

		MappedObject mapped = new MappedObject(mappable);

		for(FieldDescriptor descriptor : mapped.getFieldDescriptors()) {
			Element context = element;
			Object val = null;
			if(descriptor.hasPath()) {
				context = ElementUtils.resolvePath(element, descriptor.getPath());
			}
			if(context == null && descriptor.isRequired()) throw new MissingRequiredFieldException(
					"Required field was not found: " + descriptor.getField().getName());

			/* Only value annotation that works with null Element */
			if(descriptor.isTag()) {
				val = (context != null);
			} else {
				if(context != null)  {
					if(descriptor.hasAttribute()) {
						val = context.attributeValue(descriptor.getAttribute());
					} else if(descriptor.isText()) {
						val = context.getText();
					} else if(descriptor.isTag()) {
						val = true;
					}
				}
			}


			if(val == null && descriptor.isRequired()) throw new MissingRequiredFieldException(
					"Required field was not found: " + descriptor.getField().getName());

			descriptor.setValue(val);

		}

		return mappable;
	}

	private static void addInterpreter(Interpreter i) {
		interpreters.add(i);
	}

}
