package cn.nest.service;

import cn.nest.facde.HelloSomeOneService;

/**
 * Created by perk
 *
 * @DATE 2017/1/14
 * @DESC
 */
public class HelloSomeOneServiceImpl implements HelloSomeOneService {
    @Override
    public String saySomeOne(String s) {
        System.out.println("dubbo someone customer param value: " + s);
        /* mock verify
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return "say someone hello :" + s;
    }
}
