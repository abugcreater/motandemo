package com.fk.test.motanserver.client;

import com.fk.test.motanserver.server.HelloService;
import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;
import org.springframework.stereotype.Component;

/**
 * @ClassName HelloClient
 * @Description
 * @Author fengkai
 * @Date 2019-07-15 17:25
 * @ModifyDate 2019-07-15 17:25
 */
@Component
public class HelloClient {
    @MotanReferer
    private HelloService helloService;


//    @Test
    public void addUsers() {
        System.out.println("helloService:" + helloService);
        System.out.println(helloService.hello());
    }
}
