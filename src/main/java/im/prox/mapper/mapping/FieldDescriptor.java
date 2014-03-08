package im.prox.mapper.mapping;

import im.prox.mapper.annotation.*;
import im.prox.mapper.interpret.Interpreter;
import im.prox.mapper.interpret.InterpreterStore;

import static im.prox.mapper.utils.ReflectUtils.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FieldDescriptor {

	private Field field;
	private String path;		// @Path
	private String attribute;	// @Attribute
	private boolean text;		// @Text
	private Class listType;		// @Array
	private boolean list;		//  ""
	private boolean tag;		// @Tag

	public FieldDescriptor(Field field) throws MappingException, IllegalAnnotationException {
		this.field = field;

		path = null;
		attribute = null;
		text = false;

		List<Class<?extends Annotation>> present = new ArrayList<>();

		Attribute attributeAnnotation = getFieldAnnotation(field, Attribute.class);
		checkConflicts(present, Attribute.class);
		if(attributeAnnotation != null) {
			attribute = attributeAnnotation.value();
			present.add(Attribute.class);
		}

		Path pathAnnotation = getFieldAnnotation(field, Path.class);
		checkConflicts(present, Path.class);
		if(pathAnnotation != null) {
			path = pathAnnotation.value();
			present.add(Path.class);
		}

		Text textAnnotation = getFieldAnnotation(field, Text.class);
		checkConflicts(present, Text.class);
		if(textAnnotation != null) {
			text = true;
			present.add(Text.class);
		}

		Array listAnnotation = getFieldAnnotation(field, Array.class);
		if(listAnnotation != null) {
			if(!field.getType().isArray() && field.getType() != List.class && field.getType() != ArrayList.class) {
				throw new MappingException("The @Array annotation can only be applied to arrays and Lists!");
			}
			list = true;
			listType = listAnnotation.single();
		} else {
			list = false;
		}

		Tag tagAnnotation = getFieldAnnotation(field, Tag.class);
		tag = tagAnnotation != null;
	}

	public Field getField() {
		return field;
	}

	public String getPath() {
		return path;
	}

	public String getAttribute() {
		return attribute;
	}

	public boolean isText() {
		return text;
	}

	public Class getListType() {
		return listType;
	}

	public boolean isList() {
		return list;
	}

	/* "aliases" */
	public Class getType() {
		return field.getType();
	}

	public String getName() {
		return field.getName();
	}

	public Interpreter getInterpreter() {
		return InterpreterStore.get(field.getType());
	}

	public boolean hasInterpreter() {
		return getInterpreter() != null;
	}

	@Override
	public String toString() {
		String s = "FieldDescriptor{field=" + field.getName() + ",path=" + path + ",attribute=" + attribute;
		s += ",text=" + text + ",listType=" + listType + ",list=" + list + "}";
		return s;
	}

}
