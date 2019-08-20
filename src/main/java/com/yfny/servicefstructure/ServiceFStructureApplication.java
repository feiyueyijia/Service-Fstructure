package com.yfny.servicefstructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceFStructureApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFStructureApplication.class, args);
    }

    @RestController
    class EchoController {

        @Value("${hello.name}")
        private String name;

        @GetMapping(value = "/echo/{string}")
        public String echo(@PathVariable String string) {
            return name + " Say Hello Nacos Discovery " + string;
        }
    }

}
