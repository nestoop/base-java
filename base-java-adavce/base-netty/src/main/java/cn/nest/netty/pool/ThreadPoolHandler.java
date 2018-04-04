package cn.nest.netty.pool;

import java.util.concurrent.*;

/**
 * Created by botter
 * on 17-4-10.
 */
public class ThreadPoolHandler {

    private ExecutorService executor;

    public ThreadPoolHandler(int maxPoolSize, int queueSize) {
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void execute(Runnable task) {
        executor.execute(task);
    }
}

