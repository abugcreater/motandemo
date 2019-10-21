package com.fk.test.motanserver.admin.jvm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: fengk
 * @Description JavaClass执行工具
 * @Date: Create in 21:52 2019/10/3.
 * @Modified By:
 */
public class JavaClassExecuter {

    public static String executer(byte[] classByte){

        HackSystem.clearBuffer();
        ClassModifier cm = new ClassModifier(classByte);
        byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", "com/fk/test/motanserver/admin/jvm/HackSystem");
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class clazz = loader.loadByte(modiBytes);

        try {
            Method method = clazz.getMethod("main", new Class[]{String[].class});
            method.invoke(null, new String[]{null});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return HackSystem.getBufferString();
    }

}
