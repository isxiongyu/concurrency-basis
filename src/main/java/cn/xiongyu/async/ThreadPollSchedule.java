package cn.xiongyu.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: ThreadPoll
 * Package: cn.xiongyu.async
 * Description:
 * Date: 2020/2/28 下午2:44
 * Author: xiongyu
 */
public class ThreadPollSchedule {
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
    Thread task = null;
    ThreadPollSchedule() {
        task = new Thread(new Task());
    }
    private void startSchedule() {
        executor.scheduleAtFixedRate(task, 2000, 3000, TimeUnit.MILLISECONDS);
    }
    public void end() {
        task.interrupt();
        executor.shutdown();
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadPollSchedule schedule = new ThreadPollSchedule();
        schedule.startSchedule();
        Thread.sleep(10000);
        schedule.end();
    }
}
class Task implements Runnable {

    @Override
    public void run() {
        System.out.println("执行定时任务");
    }
}
