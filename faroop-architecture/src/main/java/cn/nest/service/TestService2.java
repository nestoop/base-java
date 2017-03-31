package cn.nest.service;

import cn.nest.annotion.ResourceAnnotation;
import cn.nest.interfaces.ITestService;

/**
 * Created by botter-common
 * on 17-3-30.
 */
@SuppressWarnings("unused")
@ResourceAnnotation(type = "test2", description = "test interface")
public class TestService2 implements ITestService {

    @Override
    public String testMethod(String code) {
        System.out.println("[TestService2] request params code = " + code);
        return "test2Method";
    }
}

