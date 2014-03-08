package im.prox.mapper.mapping;

import im.prox.mapper.utils.ElementUtils;
import org.dom4j.Element;


public class MappingFactory {

	public static Mappable map(MappedObject mapped, Element element) {
		Mappable mappable = mapped.getInstance();

		for(FieldDescriptor descriptor : mapped.getFieldDescriptors()) {
			Element context = element;
			if(descriptor.hasPath()) {
				context = ElementUtils.resolvePath(element, descriptor.getPath());
			}
			if(descriptor.hasAttribute()) {
				descriptor.setValue(context.attributeValue(descriptor.getAttribute()));
			} else if(descriptor.isText()) {
				descriptor.setValue(context.getText());
			} else if(descriptor.isTag()) {
				descriptor.setValue(context != null);
			}

		}

		return mappable;
	}

}
