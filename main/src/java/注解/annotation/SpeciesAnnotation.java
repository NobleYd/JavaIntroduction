package 注解.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/***
 * 物种
 * 
 * @author nobleyd
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SpeciesAnnotation {
	String name() default "";

}
