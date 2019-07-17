package com.fk.test.motanserver.server.impl;

import com.fk.test.motanserver.server.HelloService;
import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;
import com.weibo.api.motan.core.extension.SpiMeta;

/**
 * @ClassName HelloServiceImpl
 * @Description
 * @Author fengkai
 * @Date 2019-07-15 17:19
 * @ModifyDate 2019-07-15 17:19
 */
@MotanService(export = "demoMotan:8002", basicService = "basicServiceConfig")
@SpiMeta(name = "HelloServiceImpl")
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello() {
        return "hello world";
    }
}
