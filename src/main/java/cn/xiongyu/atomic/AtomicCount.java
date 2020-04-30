package cn.xiongyu.atomic;

import org.omg.PortableServer.THREAD_POLICY_ID;

import javax.sound.midi.Soundbank;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ClassName: AtomicCount
 * Package: cn.xiongyu.atomic
 * Description:
 * Date: 19-8-8 上午11:06
 * Author: xiongyu
 */
public class AtomicCount {
    private static final int threadTotal = 200;
    private static final int clientTotal = 5000;
    private static AtomicLong count = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    countDownLatch.countDown();
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                count.incrementAndGet();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count:" + count.get());
    }
}
