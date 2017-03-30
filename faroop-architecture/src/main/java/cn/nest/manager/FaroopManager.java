package cn.nest.manager;

import cn.nest.factory.InterfaceServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by botter
 * on 17-3-29.
 */
@Component
public class FaroopManager {

    @Autowired
    private InterfaceServiceFactory interfaceServiceFactory;


    public String testReource(String type, String code) {
        if (type == null) {
            System.out.println("type is null, type = " + type);
            return null;
        }

        return interfaceServiceFactory.getServiceImpl(type).testMethod(code);
    }
}
