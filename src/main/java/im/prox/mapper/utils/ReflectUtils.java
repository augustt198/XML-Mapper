package im.prox.mapper.utils;

import im.prox.mapper.annotation.Conflict;
import im.prox.mapper.annotation.IllegalAnnotationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class ReflectUtils {

	public static Map<Field, List<? extends Annotation>> getAnnotations(Class type) {
		Map<Field, List<? extends Annotation>> annotations = new HashMap<>();
		for(Field field : type.getDeclaredFields()) {
			List<Annotation> list = Arrays.asList(field.getAnnotations());
			annotations.put(field, list);
		}
		return annotations;
	}

	public static <T extends Annotation> T getFieldAnnotation(Field field, Class<T> type) {
		for(Annotation a : field.getAnnotations()) {
			if(a.getClass() == type) {
				return (T) a;
			}
		}
		return null;
	}

	public static <T extends Annotation> T getMetaAnnotation(Class<? extends Annotation> annot, Class<T> type) {
		for(Annotation a : annot.getDeclaredAnnotations()) {
			if(a.annotationType() == type) {
				return (T) a;
			}
		}
		return null;
	}

	public static void checkConflicts(List<Class<? extends Annotation>> present, Class<? extends Annotation> annot)
	throws IllegalAnnotationException {

		Conflict conflicts = getMetaAnnotation(annot, Conflict.class);
		if(conflicts == null) return;

		for(Class c : conflicts.value()) {
			if(present.contains(c)) {
				throw new IllegalAnnotationException(annot, c);
			}
		}
	}

}
