package test.databus;

import org.apache.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: gais.ch
 * Date: 15/5/17
 * Time: 上午9:37
 */
public class ThreadPoolUtil {

    static final Logger logger = Logger.getLogger(ThreadPoolUtil.class);

    public static final ThreadPoolExecutor asynExecutor = new ThreadPoolExecutor(
            2, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10),
            new RejectedExecutionHandler() {
                @SuppressWarnings("static-access")
                public void rejectedExecution(Runnable r,
                                              ThreadPoolExecutor executor) {
                    if (!executor.isShutdown()) {
                        try {
                            executor.getQueue().put(r);
                        } catch (InterruptedException e) {
                            r.run();
                            Thread.currentThread().interrupted();
                            logger.error("asynExecutor", e);
                        } catch (Exception e) {
                            r.run();
                            logger.error("asynExecutor", e);
                        }
                    }

                }

            });

    /**
     * 消费增量日志的线程
     * 策略：拒绝之后同步直接执行
     */
    public static final ThreadPoolExecutor deltaLogExecutor = new ThreadPoolExecutor(
            1, 15, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000),
            new RejectedExecutionHandler() {
                @SuppressWarnings("static-access")
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    r.run();
                }

            });

    public static String getDeltaLogExecutorInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("----deltaLogExecutor infomation---").append("\n");
        sb.append("max Pool Size:" + deltaLogExecutor.getMaximumPoolSize()).append("\n");
        sb.append("current Active Pool Size:" + deltaLogExecutor.getActiveCount()).append("\n");
        sb.append("Total Task Count:" + deltaLogExecutor.getTaskCount()).append("\n");
        sb.append("Completed Task Count:" + deltaLogExecutor.getCompletedTaskCount()).append("\n");
        sb.append("Block Queue remain capacity:" + deltaLogExecutor.getQueue().remainingCapacity()).append("\n");
        sb.append("------------------------\n");

        return sb.toString();
    }

}
