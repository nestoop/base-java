package cn.nest.service;

import cn.nest.annotion.ResourceAnnotation;
import cn.nest.interfaces.ITestService;

/**
 * Created by botter
 * on 17-3-29.
 */
@SuppressWarnings("unused")
@ResourceAnnotation(type = "test", description = "test interface")
public class TestService implements ITestService {

    @Override
    public String testMethod(String code) {
        System.out.println("[TestService] request params code = " + code);
        return "testMethod";
    }
}
