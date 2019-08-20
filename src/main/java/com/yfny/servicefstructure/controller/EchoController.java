package com.yfny.servicefstructure.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jisongZhou on 2019/8/20.
 **/
@RestController
@RefreshScope
public class EchoController {

    @Value("${hello.name}")
    private String name;

//    @Value("${useLocalCache}")
//    private boolean name;

    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string) {
        return name + " Say Hello Nacos Discovery " + string;
    }
}
