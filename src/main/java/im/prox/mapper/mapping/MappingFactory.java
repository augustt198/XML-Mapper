package im.prox.mapper.mapping;

import im.prox.mapper.exception.MissingRequiredFieldException;
import im.prox.mapper.utils.ElementUtils;
import org.dom4j.Element;


public class MappingFactory {

	public static Mappable map(Mappable mappable, Element element) throws MissingRequiredFieldException {

		MappedObject mapped = new MappedObject(mappable);

		for(FieldDescriptor descriptor : mapped.getFieldDescriptors()) {
			Element context = element;
			Object val = null;
			if(descriptor.hasPath()) {
				context = ElementUtils.resolvePath(element, descriptor.getPath());
			}
			if(descriptor.hasAttribute()) {
				val = context.attributeValue(descriptor.getAttribute());
			} else if(descriptor.isText()) {
				val = context.getText();
			} else if(descriptor.isTag()) {
				val = (context != null);
			}

			if(val == null) throw new MissingRequiredFieldException(
					"Required field was not found: " + descriptor.getField().getName());

			descriptor.setValue(val);

		}

		return mappable;
	}

}
