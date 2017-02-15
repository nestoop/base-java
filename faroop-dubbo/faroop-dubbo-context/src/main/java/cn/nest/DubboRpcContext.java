package cn.nest;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@SuppressWarnings("unused")
@Component
public class DubboRpcContext {

    private ApplicationConfig applicationConfig;

    private RegistryConfig registryConfig;

    @Value(value = "dubbo.application.name")
    private String name;

    @Value(value = "dubbo.registry.address")
    private String address;

    @Value(value = "dubbo.application.version")
    private String version;

    @Value(value = "dubbo.registry.protocl")
    private String protocl;

    private String username;

    private String password;

    @PostConstruct
    public void init(){
        this.applicationConfig = new ApplicationConfig(name);
        this.registryConfig = new RegistryConfig(address);
        this.registryConfig.setVersion(version);
        this.registryConfig.setCheck(false);
        this.registryConfig.setProtocol(protocl);

        if (StringUtils.isNotEmpty(username)) {
            this.registryConfig.setUsername(username);
        }

        if(StringUtils.isNotEmpty(password)) {
            this.registryConfig.setPassword(password);
        }
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public <T> T get(Class classz, String url, int retries) {

        ReferenceConfig<T> referenceConfig = new ReferenceConfig<T>();

        referenceConfig.setInterface(classz);
        referenceConfig.setUrl(url);
        referenceConfig.setRetries(retries);
        referenceConfig.setRegistry(registryConfig);

        return referenceConfig.get();
    }

    public <T> T get(Class classz, String url) {
        return get(classz, url, 0);
    }

    public <T> T get(Class classz) {
        return get(classz, null, 0);
    }

}
