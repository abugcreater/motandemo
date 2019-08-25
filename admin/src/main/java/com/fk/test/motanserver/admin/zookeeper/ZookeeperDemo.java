package com.fk.test.motanserver.admin.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: fengk
 * @Description
 * @Date: Create in 19:39 2019/8/22.
 * @Modified By:
 */
public class ZookeeperDemo implements Watcher {

    private ZooKeeper zooKeeper;

    /**
     * 超时时间
     */
    private final static int SESSION_TIME_OUT = 2000;
    //闭锁
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    //利用watch机制读取配置
    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            System.out.println("Watch received event");
            try {
                if(zooKeeper.exists("/zk", this) != null){
                    byte[] data = zooKeeper.getData("/zk", this, null);
                    System.out.println("data" + new String(data));
                }
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //满足条件释放
            countDownLatch.countDown();
        }
    }

    /**
     * 创建zookeeper链接
     *
     * @param host
     */
    public void connectZookeeper(String host) {
        try {
            zooKeeper = new ZooKeeper(host, SESSION_TIME_OUT, this);
            countDownLatch.await();
            System.out.println("connection success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建节点
     *
     * @param path
     * @param data
     * @return
     * @throws Exception
     */
    public String createNode(String path, String data) throws Exception {
        return zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 获取路径下所有节点
     *
     * @param path
     * @return
     * @throws Exception
     */
    public List<String> getChildren(String path) throws Exception {
        return zooKeeper.getChildren(path, false);
    }

    /**
     * 获取节点上数据
     *
     * @param path
     * @return
     * @throws Exception
     */
    public String getData(String path) throws Exception {
        byte[] data = zooKeeper.getData(path, false, null);
        String result = data == null ? "" : new String(data);
        return result;
    }

    /**
     * 设置节点信息，并返回节点状态信息
     *
     * @param path
     * @param data
     * @return
     * @throws Exception
     */
    public Stat setData(String path, String data) throws Exception {
        return zooKeeper.setData(path, data.getBytes(), -1);
    }

    /**
     * 删除指定路径下的节点
     *
     * @param path
     * @throws Exception
     */
    public void deleteNode(String path) throws Exception {
        zooKeeper.delete(path, -1);
    }

    /**
     * 获取创建时间
     *
     * @param path
     * @return
     * @throws Exception
     */
    public String getCTime(String path) throws Exception {
        Stat stat = zooKeeper.exists(path, false);
        return String.valueOf(stat.getCtime());
    }

    /**
     * 获取孩子节点数量
     *
     * @param path
     * @return
     * @throws Exception
     */
    public Integer getChildrenNums(String path) throws Exception {
        return zooKeeper.getChildren(path, false).size();
    }

    public void closeConnection() throws Exception {
        if (zooKeeper == null) return;
        zooKeeper.close();
    }


    public static void main(String[] args) throws Exception {
        ZookeeperDemo zookeeperDemo = new ZookeeperDemo();
        zookeeperDemo.connectZookeeper("127.0.0.1:2181");
        zookeeperDemo.createNode("/zk1", "ddd");
        zookeeperDemo.createNode("/zk1/zk2", "ddd");
        List<String> children = zookeeperDemo.getChildren("/");
        System.out.println(children);
        System.out.println("================================================");
    }

}
