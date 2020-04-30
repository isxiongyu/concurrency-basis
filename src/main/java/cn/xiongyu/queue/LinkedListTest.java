package cn.xiongyu.queue;

import java.util.LinkedList;

/**
 * ClassName: LinkedListTest
 * Package: cn.xiongyu.queue
 * Description:
 * Date: 19-10-29 下午5:03
 * Author: xiongyu
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(4);
        list.add(2);
        list.add(5);
        list.add(3);
        int a = list.poll();
        System.out.println(a);
    }
}
