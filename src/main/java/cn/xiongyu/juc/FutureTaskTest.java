package cn.xiongyu.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * ClassName: FutureTaskTest
 * Package: cn.xiongyu.juc
 * Description:
 * Date: 19-8-10 下午11:20
 * Author: xiongyu
 */
public class FutureTaskTest {

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("do something.....");
                Thread.sleep(5000);
                return "OK";
            }
        });
        new Thread(futureTask).start();
        System.out.println("do something in main");
        Thread.sleep(1000);
        //若没有返回值,线程会阻塞在该方法,直到得到返回值
        String result = futureTask.get();
        System.out.println(result);
    }
}
