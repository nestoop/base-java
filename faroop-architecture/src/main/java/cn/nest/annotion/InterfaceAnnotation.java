package cn.nest.annotion;

import java.lang.annotation.*;

/**
 * Created by botter
 * on 17-3-29.
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface InterfaceAnnotation {

    String type() default "";

    String description() default "no desc";

}
