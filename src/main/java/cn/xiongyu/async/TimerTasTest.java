package cn.xiongyu.async;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ClassName: TimerTasTest
 * Package: cn.xiongyu.async
 * Description:
 * Date: 2020/2/28 下午2:48
 * Author: xiongyu
 */
public class TimerTasTest {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("执行定时任务");
            }
        }, 1000, 2000);
    }
}
