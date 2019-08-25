package com.fk.test.motanserver.admin.zookeeper.lock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: fengk
 * @Description
 * @Date: Create in 16:31 2019/8/23.
 * @Modified By:
 */
public class Locks extends TestMainClient {

    String myZnode;

    //初始化时创建需要的节点
    public Locks(String connectString, String root) {
        super(connectString);
        this.root = root;
        if (zk != null) {
            try {
                Stat stat = zk.exists(root, false);
                if (stat == null) {
                    zk.create(root, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                }
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取锁
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void getLock() throws KeeperException, InterruptedException {
        List<String> childrens = zk.getChildren(root, false);
        String[] nodes = childrens.toArray(new String[childrens.size()]);
        Arrays.sort(nodes);
        if (myZnode.equals(root + "/" + nodes[0])) {
            System.out.println("do actions =====");
        } else {
            waitForLock(nodes[0]);
        }
    }

    public void waitForLock(String lower) throws InterruptedException, KeeperException {
        Stat stat = zk.exists(root + "/" + lower, true);
        if (stat != null) {
            mutex.wait();
        } else {
            getLock();
        }
    }

    public void check() throws InterruptedException, KeeperException {
        myZnode = zk.create(root + "/lock_", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        getLock();
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == Event.EventType.NodeDeleted) {
            System.out.println("得到通知");
            super.process(event);
            System.out.println("do actions =====");
        }
    }

    public static void main(String[] args) {

        String connectString = "localhost:2181";
        Locks lk = new Locks(connectString, "/locks");
        try {
            lk.check();
        } catch (InterruptedException e) {

        } catch (KeeperException e) {

        }


    }

}
