package cn.nest.annotion;


import org.springframework.aop.framework.AopProxyUtils;

import java.lang.annotation.Annotation;

/**
 * Created by botter
 * on 17-3-29.
 * Aop util
 */
public class AopAnnotationUtil {

    public static <Obj, T extends Annotation> T getAnnotation(Obj obj, Class<T> annotationClass) {
        if (obj == null || annotationClass == null) {
            return null;
        }

        return getAnnotation(AopProxyUtils.ultimateTargetClass(obj), annotationClass);
    }

    private static  <T extends Annotation> T getAnnotation(Class<?> targetClass, Class<T> annotationClass) {
        if (targetClass == null || annotationClass == null) {
            return null;
        }
       return targetClass.getAnnotation(annotationClass);
    }

    public static <Obj> String getResourceType(Obj obj) {
        if (obj == null) {
            return null;
        }

        InterfaceAnnotation annotation = getAnnotation(obj, InterfaceAnnotation.class);

        return annotation == null?null:annotation.type();
    }
}
