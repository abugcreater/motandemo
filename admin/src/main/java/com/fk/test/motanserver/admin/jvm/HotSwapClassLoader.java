package com.fk.test.motanserver.admin.jvm;

/**
 * @Author: fengk
 * @Description 为了多次载入执行类而加入的加载器
 *              把defineClass方法开放出来，只有外部显示调用的时候才会使用到loadByte方法
 *              由虚拟机调用时，任然按照原有的双亲委派规则使用loadclass方法进行类加载
 * @Date: Create in 20:47 2019/10/3.
 * @Modified By:
 */
public class HotSwapClassLoader extends ClassLoader{

    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    /**
     * 把提交执行的Java类的byte数组转变为class对象
     * @param classByte
     * @return
     */
    public Class loadByte(byte[] classByte){
        return defineClass(null, classByte, 0, classByte.length);
    }

}
