package im.prox.mapper.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation is used to indicate that the value of the annotated
 * field should be assigned to the value of the text in the tag
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Text {

}
