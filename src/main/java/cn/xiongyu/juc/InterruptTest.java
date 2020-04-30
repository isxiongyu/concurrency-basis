package cn.xiongyu.juc;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ClassName: InterruptTest
 * Package: cn.xiongyu.juc
 * Description:
 * Date: 19-9-11 下午3:02
 * Author: xiongyu
 */
public class InterruptTest extends Thread {
    private static final BlockingQueue<BigInteger> queue = new ArrayBlockingQueue<>(10000);
    public void run() {
        BigInteger i = BigInteger.ONE;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                queue.put(i = i.nextProbablePrime());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void cancel() {
        interrupt();
    }
    public static void main(String[] args) throws InterruptedException {
        InterruptTest interruptTest = new InterruptTest();
        interruptTest.start();
        Thread.sleep(3000);
        interruptTest.cancel();
        while (queue.size() != 0) {
            System.out.println(queue.take());
        }
    }
}
