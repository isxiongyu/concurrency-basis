package cn.xiongyu.juc;

import java.util.concurrent.*;

/**
 * ClassName: CyclicBarrierTest
 * Package: cn.xiongyu.juc
 * Description:
 * Date: 19-8-9 下午9:14
 * Author: xiongyu
 */
public class CyclicBarrierTest {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            executorService.execute(() -> {
                add();
            });
        }
        executorService.shutdown();
    }
    private static void add(){
        System.out.println(Thread.currentThread().getId() + "thread is ready");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getId() + "thread is continue");
    }
}
