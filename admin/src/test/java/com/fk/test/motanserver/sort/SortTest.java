package com.fk.test.motanserver.sort;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Method;

/**
 * @Author: fengk
 * @Description
 * @Date: Create in 20:35 2019/9/24.
 * @Modified By:
 */
public class SortTest {

    public static void main(String[] args) throws Exception {
        Class sort = SortTest.class;
        Object obj = sort.newInstance();
        SortTest sortTest = null;
        if(obj instanceof  SortTest){
            sortTest = (SortTest) obj;
        }
        int[] array = {3,5,2,7,9,1,6};
//        Method method = sort.getMethod("selectionSort", int[].class);
//        method.invoke(sortTest, array);

        Method method1 = sort.getMethod("insertSort", int[].class);
        method1.invoke(sortTest, array);

    }

    /**
     * 选择排序步骤
     * 1. 从左到右找出最小的一个数并标记位置
     * 2. 将最小的放到起始检查点
     * 3. 循环到最后一位
     * @param array
     */
    public void selectionSort(int[] array) {
        if(!verificate(array)) return;
        int length = array.length;
        for(int k = 0;k < length; k++){
            int min = k;
            for(int i = k + 1;i < length ; i++){
                if(array[min] > array[i]){
                    min = i;
                }
            }
            if(min != k){
                int temp = array[k];
                array[k] = array[min];
                array[min] = temp;
                System.out.println(JSON.toJSON(array) + "min" + min);
            }
        }
    }


    /**
     * 插入排序
     * 1.选择起始比较数，比较该数据与左侧数据
     * 2.如果左侧比较大则向右移动
     * 3.循环比较到最后一位
     * @param array
     */
    public void insertSort(int[]array){
        if(!verificate(array)) return;
        if(array.length == 1) return;
        for(int i = 1; i < array.length; i++){
            int j = i;
            int sub = array[i];
            while (j > 0 && array[j - 1] > sub){
                array[j] = array[j - 1];
                j --;
            }
            array[j] = sub;
            System.out.println(JSON.toJSON(array));
        }




    }

    /**
     * 快速排序步骤
     * 1.
     *
     * @param array
     */
    public void  quickSort(int[]array){

    }


    private boolean verificate(int[] array){

        if(array == null || array.length == 0){
            return false;
        }

        return true;
    }


}
