package im.prox.mapper.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectUtils {

	public static Map<Field, List<? extends Annotation>> getAnnotations(Class CLASS) {
		Map<Field, List<? extends Annotation>> annotations = new HashMap<>();
		for(Field field : CLASS.getDeclaredFields()) {
			List<Annotation> list = Arrays.asList(field.getAnnotations());
			annotations.put(field, list);
		}
		return annotations;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Annotation> T getFieldAnnotation(Field field, Class<T> type) {
		for(Annotation a : field.getAnnotations()) {
			if(a.getClass() == type) {
				return (T) a;
			}
		}
		return null;
	}

}
