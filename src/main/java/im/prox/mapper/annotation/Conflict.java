package im.prox.mapper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Meta annotation for marking conflicts between annotations
 * e.g. A field can't both be assigned to the text and attribute
 * of an Element.
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Conflict {

	public Class[] value();

}
