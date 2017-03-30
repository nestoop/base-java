package cn.nest.annotion;

import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;

/**
 * Created by botter
 * on 17-3-29.
 * Aop util
 */
public class AopUtil {

    public static String getAnnotionType(Class classz) throws NoSuchFieldException {
        Class<?> annotionClass = AopUtils.getTargetClass(classz);
        Field type = annotionClass.getDeclaredField("type");
          //get annotion of type value
//        type.get();
        return "";
    }
}
