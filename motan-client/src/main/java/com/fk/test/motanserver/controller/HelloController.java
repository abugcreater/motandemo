package com.fk.test.motanserver.controller;

import com.fk.test.motanserver.client.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description
 * @Author fengkai
 * @Date 2019-07-15 17:39
 * @ModifyDate 2019-07-15 17:39
 */
@RequestMapping("/hello")
@RestController
public class HelloController {

    @Autowired
    private HelloClient helloClient;

    @RequestMapping("/test")
    public String test1(){
        helloClient.addUsers();
        return "success";
    }

}
