package im.prox.mapper.mapping;

import im.prox.mapper.annotation.*;
import im.prox.mapper.exception.IllegalAnnotationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MappedObject {

	public static final Class[] allAnnotations = {
			Attribute.class, Path.class,
			Text.class, Root.class,
			Tag.class, Array.class
	};

	private Mappable mappable;
	private List<FieldDescriptor> fields;

	public MappedObject(Mappable mappable) {
		this.mappable = mappable;
		fields = new ArrayList<>();
		for(Field field : mappable.getClass().getDeclaredFields()) {

			boolean isAnnotated = false;
			/* Ensure that the given field has at least one XML mapping annotation */
			for(Annotation a : field.getAnnotations()) {
				for(Class c : allAnnotations) {
					if(a.annotationType() == c) {
						isAnnotated = true;
					}
				}

			}

			if(!isAnnotated) continue;

			try {
				fields.add(new FieldDescriptor(field, this));
			} catch(MappingException | IllegalAnnotationException e) {
				e.printStackTrace();
			}
		}
	}

	public Mappable getInstance() {
		return mappable;
	}

	public List<FieldDescriptor> getFieldDescriptors() {
		return fields;
	}

}