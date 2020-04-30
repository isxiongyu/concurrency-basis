package cn.xiongyu.juc;

import java.util.Locale;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: CyclicBarrierTest
 * Package: cn.xiongyu.juc
 * Description:
 * Date: 19-8-9 下午9:14
 * Author: xiongyu
 */
public class ReentrantLockTest {

    private static int count = 0;

    private static ReentrantLock lock = new ReentrantLock();
    private static CountDownLatch countDownLatch = new CountDownLatch(5000);
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5000; i++) {
            executorService.execute(() -> {
                lock.lock();
                try {
                    add();
                } finally {
                    lock.unlock();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(count);
    }
    private static void add(){
        count++;
    }
}
