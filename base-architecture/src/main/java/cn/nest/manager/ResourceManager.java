package cn.nest.manager;

import cn.nest.factory.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by botter
 * on 17-3-29.
 */
@Component
public class ResourceManager {

    @Autowired
    private ResourceFactory resourceFactory;


    public String testReource(String type, String code) {
        if (type == null) {
            System.out.println("type is null, type = " + type);
            return null;
        }

        return resourceFactory.getServiceImpl(type).testMethod(code);
    }
}
