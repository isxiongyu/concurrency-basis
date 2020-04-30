package cn.xiongyu.pc;

import org.junit.jupiter.api.Test;

import javax.validation.constraints.Size;
import java.security.PublicKey;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: Product
 * Package: cn.xiongyu.pc
 * Description:
 * Date: 2020/3/6 下午3:28
 * Author: xiongyu
 */
public class ProducerConsumer {
    static Lock lock = new ReentrantLock();
    static Condition notFull = lock.newCondition();
    static Condition notEmpty = lock.newCondition();
    Queue<Integer> queue = new LinkedList<>();
    int size;
    ProducerConsumer(int size) {
        this.size = size;
    }
    public void producer() {
        new Thread(()->{
            while (true) {
                try {
                    lock.lock();
                    if (queue.size() == size) {
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.add(queue.size());
                    notEmpty.signal();
                    System.out.println("producer" + queue);
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }
    public void consumer() {
        new Thread(()->{
            while (true) {
                try {
                    lock.lock();
                    if (queue.size() == 0) {
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll();
                    notFull.signal();
                    System.out.println("consumer" + queue);
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer(5);
        pc.producer();
        pc.consumer();
    }
}
