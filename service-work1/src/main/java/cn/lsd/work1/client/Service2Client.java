package cn.lsd.work1.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-work2", fallback = Service2Client.Service2ClientFallback.class)
public interface Service2Client {

    @GetMapping(value = "/")
    String printService2();

    @Component
    class Service2ClientFallback implements Service2Client {

        private static final Logger LOGGER = LoggerFactory.getLogger(Service2ClientFallback.class);

        @Override
        public String printService2() {
            LOGGER.info("异常发生，进入fallback方法");
            return "SERVICE B FAILED! - FALLING BACK";
        }
    }
}