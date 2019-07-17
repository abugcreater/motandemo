package com.fk.test.motanserver.config;

import com.weibo.api.motan.config.springsupport.*;
import org.springframework.beans.factory.annotation.Value;
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
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setPackage("com.fk.test");
        return annotationBean;
    }

    @Bean(name = "motan")
    public ProtocolConfigBean protocolConfig() {
        ProtocolConfigBean config = new ProtocolConfigBean();
        config.setDefault(true);
        config.setSerialization("hessian2");
        config.setName("motan");
        config.setMaxContentLength(1548576);
        config.setRequestTimeout(50000000);
        return config;
    }

    @Bean(name = "registry")
    public RegistryConfigBean registryConfigBean() {
        RegistryConfigBean config = new RegistryConfigBean();
        config.setDefault(true);
        config.setRegProtocol("zookeeper");
        config.setAddress("127.0.0.1:2181");
        return config;
    }


    @Bean(name = "motanClientBasicConfig")
    public BasicRefererConfigBean baseRefererConfig() {
        BasicRefererConfigBean config = new BasicRefererConfigBean();
        config.setProtocol("motan");
        config.setGroup("sinomall");
        config.setAccessLog(false);
        config.setRegistry("registry");
        config.setCheck(false);
        config.setRetries(0);
        config.setThrowException(true);
        config.setRequestTimeout(9000);
        config.setDefault(true);
        return config;
    }

}
