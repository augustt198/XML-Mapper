package im.prox.mapper.mapping;

import java.lang.reflect.Field;
import java.util.List;

public class MappedObject {

	private Object object;
	private List<FieldDescriptor> fields;

	public MappedObject(Object object) {
		this.object = object;
		for(Field field : object.getClass().getFields()) {
			fields.add(new FieldDescriptor(field));
		}
	}

	public Object getObject() {
		return object;
	}

	public List<FieldDescriptor> getFieldDescriptors() {
		return fields;
	}
}