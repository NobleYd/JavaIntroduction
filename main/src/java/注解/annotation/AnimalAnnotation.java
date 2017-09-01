package 注解.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/***
 * 动物
 * 
 * @author nobleyd
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@SpeciesAnnotation
@Inherited
public @interface AnimalAnnotation {

	String name() default "";

}
