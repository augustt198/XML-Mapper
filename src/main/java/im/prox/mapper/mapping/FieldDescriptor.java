package im.prox.mapper.mapping;

import im.prox.mapper.annotation.Attribute;
import im.prox.mapper.annotation.List;
import im.prox.mapper.annotation.Path;
import im.prox.mapper.annotation.Text;
import im.prox.mapper.interpret.Interpreter;
import im.prox.mapper.interpret.InterpreterStore;

import static im.prox.mapper.utils.ReflectUtils.*;

import java.lang.reflect.Field;

public class FieldDescriptor {

	private Field field;
	private String path;		// @Path
	private String attribute;	// @Attribute
	private boolean text;		// @Text
	private Class listType;		// @List
	private boolean list;		//  ""

	public FieldDescriptor(Field field) {
		this.field = field;

		Attribute attributeAnnotation = getFieldAnnotation(field, Attribute.class);
		attribute = attributeAnnotation == null ?  null : attributeAnnotation.value();

		Path pathAnnotation = getFieldAnnotation(field, Path.class);
		path = pathAnnotation == null ? null : pathAnnotation.value();

		Text textAnnotation = getFieldAnnotation(field, Text.class);
		text = textAnnotation != null;

		List listAnnotation = getFieldAnnotation(field, List.class);
		if(listAnnotation != null) {
			list = true;
			listType = listAnnotation.single();
		} else {
			list = false;
		}

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

}
