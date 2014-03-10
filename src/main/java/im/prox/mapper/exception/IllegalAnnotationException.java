package im.prox.mapper.exception;

import java.lang.annotation.Annotation;

public class IllegalAnnotationException extends MappingException {

	public IllegalAnnotationException(Class<? extends Annotation> illegal, Class<? extends Annotation> annotation) {
		super(
		"Could not apply annotation " + illegal.getSimpleName() + " because the annotation " +
		 annotation.getSimpleName() + " is present"
		);
	}

}
