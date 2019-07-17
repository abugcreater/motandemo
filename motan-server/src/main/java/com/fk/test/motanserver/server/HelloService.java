package com.fk.test.motanserver.server;

import com.weibo.api.motan.core.extension.Scope;
import com.weibo.api.motan.core.extension.Spi;

/**
 * @ClassName HelloService
 * @Description
 * @Author fengkai
 * @Date 2019-07-15 17:18
 * @ModifyDate 2019-07-15 17:18
 */
@Spi(scope = Scope.SINGLETON)
public interface HelloService {

    String hello();
}
