package cn.nest.service;

import cn.nest.facde.HelloService;

/**
 * Created by perk
 *
 * @DATE 2017/1/14
 * @DESC
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String s) {
        System.out.println("dubbo customer param value: " + s);
        return "say hello :" + s;
    }
}
