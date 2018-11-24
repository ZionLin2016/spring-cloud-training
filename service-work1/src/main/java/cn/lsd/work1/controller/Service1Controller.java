package cn.lsd.work1.controller;

import cn.lsd.work1.client.Service2Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RefreshScope
@RestController
public class Service1Controller {

    @Value("${name:unknown}")
    private String name;

    @Autowired
    EurekaDiscoveryClient discoveryClient;
    @Autowired
    private Service2Client service2Client;

    @GetMapping(value = "/")
    public String printServiceA() {
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        return serviceInstance.getServiceId() + " (" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + ")" + "===>name:" + name + "<br/>" + service2Client.printService2();
    }

    @GetMapping(path = "/current")
    public Principal getCurrentAccount(Principal principal) {
        return principal;
    }
}