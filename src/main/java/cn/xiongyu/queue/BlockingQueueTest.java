package cn.xiongyu.queue;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: BlockingQueueTest
 * Package: cn.xiongyu.queue
 * Description:
 * Date: 19-10-29 下午4:33
 * Author: xiongyu
 */
public class BlockingQueueTest {

    private static ArrayBlockingQueue<Integer> queue = new
            ArrayBlockingQueue<Integer>(10);
    private static Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    class Consumer extends Thread {
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll(); // 每次移走队首元素
                    System.out.println("移走元素");
                    notFull.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Producer extends Thread {
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == 10) {
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.offer(1); // 每次插入一个元素
                    System.out.println("插入元素");
                    notEmpty.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueueTest queueTest = new BlockingQueueTest();
        Thread thread1 = new Thread(queueTest.new Consumer());
        Thread thread2 = new Thread(queueTest.new Producer());
        thread1.start();
        thread2.start();
//        System.out.println(queue.take());

    }
}
