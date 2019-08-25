package com.fk.test.motanserver.admin.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.*;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;
import org.apache.zookeeper.data.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加ACL权限
 */
public class NewDigest {
    public static void main(String[] args) throws Exception {//new一个acl
        List<ACL> acls = new ArrayList<ACL>();
        //添加第一个id，采用用户名密码形式
        Id id1 = new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin"));
        ACL acl1 = new ACL(ZooDefs.Perms.ALL, id1);
        acls.add(acl1);
        //添加第二个id，所有用户可读权限
        Id id2 = new Id("world", "anyone");
        ACL acl2 = new ACL(ZooDefs.Perms.READ, id2);
        acls.add(acl2);
        // Zk用admin认证，创建/test ZNode。
        ZooKeeper Zk = new ZooKeeper("host1:2181,host2:2181,host3:2181", 2000, null);
        Zk.addAuthInfo("digest", "admin:admin".getBytes());
        Zk.create("/test", "data".getBytes(), acls, CreateMode.PERSISTENT);

    }
}