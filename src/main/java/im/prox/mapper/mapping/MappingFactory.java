package im.prox.mapper.mapping;

import im.prox.mapper.exception.MappingException;
import im.prox.mapper.exception.MissingRequiredFieldException;
import im.prox.mapper.interpret.Interpreter;
import im.prox.mapper.utils.ElementUtils;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MappingFactory {

	private static List<Interpreter> interpreters = new ArrayList<>();

	@SuppressWarnings("deprecated")
	public static <T extends Mappable> T map(T mappable, Element element) throws MappingException {

		MappedObject mapped = new MappedObject(mappable);

		for(FieldDescriptor descriptor : mapped.getFieldDescriptors()) {
			Element context = element;
			String val = null;
			if(descriptor.hasPath()) {
				context = ElementUtils.resolvePath(element, descriptor.getPath());
			}
			if(context == null && descriptor.isRequired()) throw new MissingRequiredFieldException(
					"Required field was not found: " + descriptor.getField().getName());

			/* Only value annotation that works with null Element */
			if(descriptor.isTag()) {
				descriptor.setValue(context != null);
				continue;
			} else {
				if(context != null)  {
					if(descriptor.hasAttribute()) {
						val = context.attributeValue(descriptor.getAttribute());
					} else if(descriptor.isText()) {
						val = context.getText();
					}
				}
			}

			Class type = descriptor.getType();

			Object obj = null;

			if(type == Integer.class) {
				obj = Integer.valueOf(val);
			} else if(type == Long.class) {
				obj = Long.valueOf(val);
			} else if(type == Float.class) {
				obj = Float.valueOf(val);
			} else if(type == Double.class) {
				obj = Float.valueOf(val);
			} else if(type == Byte.class) {
				obj = Byte.valueOf(val);
			} else if(type == Boolean.class) {
				obj = Boolean.valueOf(val);
			} else if(type == Date.class) {
				obj = new Date(Date.parse(val));
			} else {
				Interpreter interpreter = getInterpreter(type);
				if(interpreter == null) {
					obj = null;
				} else {
					obj = interpreter.convert(val);
				}
			}

			if(obj == null && descriptor.isRequired()) throw new MissingRequiredFieldException(
					"Required field was not found: " + descriptor.getField().getName());

			descriptor.setValue(obj);

		}

		return mappable;
	}

	@SuppressWarnings("unchecked")
	private static <T> Interpreter<T> getInterpreter(Class<T> type) {
		for(Interpreter i : interpreters) {
			if(i.getType() == type) {
				return i;
			}
		}
		return null;
	}

	public static void addInterpreter(Interpreter i) {
		interpreters.add(i);
	}

}
