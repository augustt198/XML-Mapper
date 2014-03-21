package im.prox.mapper.mapping;

import im.prox.mapper.annotation.*;
import im.prox.mapper.exception.MappingException;

import static im.prox.mapper.utils.ReflectUtils.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FieldDescriptor {

	private MappedObject base;
	private Field field;
	private String path;		// @Path
	private String attribute;	// @Attribute
	private boolean text;		// @Text
	private Class listType;		// @Array type
	private boolean array;		// @Array
	private boolean tag;		// @Tag
	private boolean required;	// @Required

	public FieldDescriptor(Field field, MappedObject base) throws MappingException {
		this.field = field;
		this.base = base;

		path = null;
		attribute = null;
		text = false;

		List<Class<?extends Annotation>> present = new ArrayList<>();

		Attribute attributeAnnotation = getFieldAnnotation(field, Attribute.class);
		if(attributeAnnotation != null) {
			checkConflicts(present, Attribute.class);
			attribute = attributeAnnotation.value();
			present.add(Attribute.class);
		}

		Path pathAnnotation = getFieldAnnotation(field, Path.class);
		if(pathAnnotation != null) {
			checkConflicts(present, Path.class);
			path = pathAnnotation.value();
			present.add(Path.class);
		}

		Text textAnnotation = getFieldAnnotation(field, Text.class);
		if(textAnnotation != null) {
			checkConflicts(present, Text.class);
			text = true;
			present.add(Text.class);
		}

		Array listAnnotation = getFieldAnnotation(field, Array.class);
		if(listAnnotation != null) {
			if(!field.getType().isArray() && field.getType() != List.class && field.getType() != ArrayList.class) {
				throw new MappingException("The @Array annotation can only be applied to arrays and Lists!");
			}
			array = true;
			listType = listAnnotation.single();
		} else {
			array = false;
		}

		Required requiredAnnotation = getFieldAnnotation(field, Required.class);
		required = requiredAnnotation != null;

		Tag tagAnnotation = getFieldAnnotation(field, Tag.class);
		tag = tagAnnotation != null;
		if(tag && field.getType() != Boolean.class) {
			throw new MappingException("The @Tag annotation can only be applied to boolean types");
		}
	}

	public Field getField() {
		return field;
	}

	public String getPath() {
		return path;
	}

	public boolean hasPath() {
		return path != null;
	}

	public String getAttribute() {
		return attribute;
	}

	public boolean hasAttribute() {
		return attribute != null;
	}

	public boolean isText() {
		return text;
	}

	public Class getArrayType() {
		return listType;
	}

	public boolean isArray() {
		return array;
	}

	public boolean isTag() {
		return tag;
	}

	public boolean isRequired() {
		return required;
	}

	/* "aliases" */
	public Class getType() {
		return field.getType();
	}

	public String getName() {
		return field.getName();
	}

	public void setValue(Object o) {
		field.setAccessible(true);
		try {
			field.set(base.getInstance(), o);
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		String s = "FieldDescriptor{field=" + field.getName() + ",path=" + path + ",attribute=" + attribute;
		s += ",text=" + text + ",listType=" + listType + ",list=" + array + ",type=" + field.getType().getSimpleName()+ "}";
		return s;
	}

}
