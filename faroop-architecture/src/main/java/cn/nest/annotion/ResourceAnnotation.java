package cn.nest.annotion;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by botter
 * on 17-3-29.
 */

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Inherited
public @interface ResourceAnnotation {

    String type() default "";

    String description() default "no desc";

}
