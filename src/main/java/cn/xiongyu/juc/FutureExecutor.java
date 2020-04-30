package cn.xiongyu.juc;

import java.util.concurrent.*;

/**
 * ClassName: FutureTest
 * Package: cn.xiongyu.juc
 * Description:
 * Date: 19-9-11 上午10:10
 * Author: xiongyu
 */
public class FutureExecutor {
    private static final ExecutorService executor = Executors.newFixedThreadPool(50);

    public static void main(String[] args) {
        Callable<String> task = () -> {
            Thread.sleep(3000);
            return "执行callable";
        };
        Future<String> future = executor.submit(task);
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            future.cancel(true);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
