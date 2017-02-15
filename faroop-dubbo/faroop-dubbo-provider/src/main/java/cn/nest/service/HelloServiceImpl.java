package cn.nest.service;

import cn.nest.facde.HelloService;
import com.alibaba.dubbo.rpc.RpcContext;

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
        /* mock verify
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        boolean isCustomer = RpcContext.getContext().isProviderSide();
        System.out.println("provider iscustomer :" + isCustomer);

        return "say hello :" + s;
    }
}
