package im.prox.mapper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to indicate the presence of an Element
 */
@Conflict({
		Text.class,
		Array.class,
		Attribute.class
})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tag {

}
