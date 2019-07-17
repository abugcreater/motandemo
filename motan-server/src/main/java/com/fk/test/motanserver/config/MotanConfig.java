package com.fk.test.motanserver.config;

import com.fk.test.motanserver.server.HelloService;
import com.fk.test.motanserver.server.impl.HelloServiceImpl;
import com.weibo.api.motan.config.springsupport.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MotanConfig
 * @Description
 * @Author fengkai
 * @Date 2019-07-15 16:55
 * @ModifyDate 2019-07-15 16:55
 */
@Configuration
public class MotanConfig {

    @Bean
    public AnnotationBean motanAnnotationBean() {
        AnnotationBean motanAnnotationBean = new AnnotationBean();
        motanAnnotationBean.setPackage("com.fk.test.motanserver");
        return motanAnnotationBean;
    }

    @Bean(name = "demoMotan")
    public ProtocolConfigBean protocolConfig1() {
        ProtocolConfigBean config = new ProtocolConfigBean();
        config.setDefault(true);
        config.setName("motan");
        config.setMaxContentLength(1048576);
        return config;
    }



    @Bean(name = "registryConfig1")
    public RegistryConfigBean registryConfig() {
        RegistryConfigBean config = new RegistryConfigBean();
        config.setRegProtocol("local");
//        config.setName("sdzz_zookeeper");
//        config.setAddress("127.0.0.1:2181");
        return config;
    }


    @Bean(name = "basicRefererConfig")
    public BasicRefererConfigBean basicRefererConfigBean() {
        BasicRefererConfigBean config = new BasicRefererConfigBean();
        config.setShareChannel( true);
        config.setCheck( false);
        config.setRequestTimeout(30000);
        config.setRegistry("registryConfig1");
        config.setAccessLog(false);
        return config;
    }

    @Bean(name = "basicServiceConfig")
    public BasicServiceConfigBean baseServiceConfig() {
        BasicServiceConfigBean config = new BasicServiceConfigBean();
        config.setExport("demoMotan:8002");
        config.setGroup("testgroup");
        config.setAccessLog(false);
        config.setShareChannel(true);
        config.setModule("motan-demo-rpc");
        config.setApplication("myMotanDemo");
        config.setRegistry("registryConfig1");
        return config;
    }




}
