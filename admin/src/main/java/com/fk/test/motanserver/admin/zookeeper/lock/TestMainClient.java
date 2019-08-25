package com.fk.test.motanserver.admin.zookeeper.lock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @Author: fengk
 * @Description
 * @Date: Create in 16:23 2019/8/23.
 * @Modified By:
 */
public class TestMainClient implements Watcher {

    protected static ZooKeeper zk = null;
    protected static Integer mutex;
     int sessionTimeOut = 2000;
    protected String root;

    /**
     * 构造函数中初始化连接
     * @param connectString
     */
    public TestMainClient(String connectString) {
        try {
            zk = new ZooKeeper(connectString, sessionTimeOut, this);
            mutex = new Integer(-1);
        } catch (IOException e) {
            zk = null;
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        synchronized (mutex){
            mutex.notify();
        }
    }
}
