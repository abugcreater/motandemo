package com.fk.test.motanserver.admin.jvm;

/**
 * @Author: fengk
 * @Description byte数组处理工具
 * @Date: Create in 21:10 2019/10/3.
 * @Modified By:
 */
public class ByteUtils {

    //byte数组转化为int
    public static int bytes2Int(byte[] b, int start, int len){

        int sum = 0;
        int end = start + len;
        for (int i = start; i < end; i++){
            int n = ((int) b[i]) & 0xff;
            n <<= (--len) * 8;
            sum += n;
        }
        return  sum;
    }

    //int转化为byte
    public static byte[] int2Bytes(int value, int len){
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++ ){
            b[len - i -1] = (byte)((value >> 8 * i) & 0xff);
        }
        return b;
    }

    //byte转化为string
    public static String bytes2String(byte[]b, int start, int len){
        return new String(b, start, len);
    }

    //string转化为byte
    public static byte[] string2Bytes(String str){
        return str.getBytes();
    }

    //byte数组替换
    public static byte[] bytesReplace(byte[] originalByte, int offset, int len, byte[] replaceBytes){
        byte[] newBytes = new byte[originalByte.length + (replaceBytes.length - len)];
        System.arraycopy(originalByte, 0 , newBytes,0 ,offset);
        System.arraycopy(replaceBytes, 0 , newBytes, offset, replaceBytes.length);
        System.arraycopy(originalByte, offset + len , newBytes, offset + replaceBytes.length,
                originalByte.length - offset - len);

        return newBytes;

    }

}
