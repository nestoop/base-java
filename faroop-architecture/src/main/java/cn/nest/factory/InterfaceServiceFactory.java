package cn.nest.factory;

import cn.nest.annotion.AopAnnotationUtil;
import cn.nest.annotion.ResourceAnnotation;
import cn.nest.interfaces.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by botter
 * on 17-3-30.
 */
@SuppressWarnings("unused")
@Component
public class InterfaceServiceFactory {

    private static Map<String, ITestService> testMap;

    public InterfaceServiceFactory () {
        testMap = new ConcurrentHashMap<>();
    }

    @Autowired(required = false)
    public void init(ITestService[] iTestServices) {

        if (iTestServices == null || iTestServices.length == 0) {
            System.out.println("no implement ITestService interface");
        }
        //get all use InterfaceAnnotion
        for (ITestService iTestService : iTestServices) {
            //get type interface implement class
            ResourceAnnotation annotation = AopAnnotationUtil.getAnnotation(iTestService, ResourceAnnotation.class);

            if (annotation != null) {
                if (testMap.containsKey(annotation.type())) {
                    System.out.println("[InterfaceServiceFactory] map is readly exists implement....");
                } else {
                    System.out.println("[InterfaceServiceFactory] map is not exists implement,then put map tyepe = " + annotation.type());
                    testMap.put(annotation.type(), iTestService) ;
                }
            }

        }
    }

    public ITestService getServiceImpl(String type) {

        if (!testMap.containsKey(type)) {
            System.out.println("[InterfaceServiceFactory] map is not exists ITestService");
            return null;
        }

        return testMap.get(type);
    }
}
