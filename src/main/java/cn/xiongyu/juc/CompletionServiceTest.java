package cn.xiongyu.juc;

import java.util.concurrent.*;

/**
 * ClassName: CompletionServiceTest
 * Package: cn.xiongyu.juc
 * Description:
 * Date: 19-9-11 上午10:25
 * Author: xiongyu
 */
public class CompletionServiceTest {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);
        for (int i = 0; i < 10; i++) {
            final int val = i;
            Callable<Integer> task = () -> {
                Thread.sleep(5000);
                return val;
            };
            completionService.submit(task);
        }
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = completionService.take();
            int val = future.get();
            System.out.println(val);
        }
        executor.shutdown();
    }
}
