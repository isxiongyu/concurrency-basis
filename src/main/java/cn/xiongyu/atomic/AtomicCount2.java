package cn.xiongyu.atomic;

import javax.sound.midi.Soundbank;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ClassName: AtomicCount
 * Package: cn.xiongyu.atomic
 * Description:
 * Date: 19-8-8 上午11:06
 * Author: xiongyu
 */
public class AtomicCount2 {
    private static final int threadTotal = 200;
    private static final int clientTotal = 5000;
    private static long count = 0;

    private static CountDownLatch countDownLatch = new CountDownLatch(1000000);
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadTotal; i++) {
            executorService.execute(() -> {
                for(int j = 0; j < clientTotal; j++){
                    add();
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count:" + count);
    }
    private synchronized static void add(){
        count++;
    }
}
