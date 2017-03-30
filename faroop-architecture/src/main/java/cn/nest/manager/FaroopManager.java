package cn.nest.manager;

import cn.nest.annotion.AopUtil;
import cn.nest.annotion.InterfaceAnnotation;
import cn.nest.interfaces.ITestService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by botter
 * on 17-3-29.
 */
@Component
public class FaroopManager {

    private static Map<String, ITestService> testMap;

    public FaroopManager() {
        testMap = new ConcurrentHashMap<>();
    }

    @PostConstruct
    public void initTestInterface(ITestService[] iTestServices) {

        //get all use InterfaceAnnotion

        for (ITestService iTestService : iTestServices) {

            //get type interface implement class
            String type = "";
            if (testMap.containsKey(type)) {

            } else {
                testMap.put(type, iTestService) ;
            }
        }
    }

    public String testReource(String type, String code) {
        if (!testMap.containsKey(type)) {
            System.out.println("no useful testResource");
            return null;
        }

        return testMap.get(type).testMethod(code);
    }
}
