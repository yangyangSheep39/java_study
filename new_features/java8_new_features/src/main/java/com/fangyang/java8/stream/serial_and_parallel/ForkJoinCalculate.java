package com.fangyang.java8.stream.serial_and_parallel;

import java.util.concurrent.RecursiveTask;

/**
 * ### Fork/Join框架与传统线程池的区别
 * <p>
 * 采用 “工作窃取”模式（work-stealing）：
 * <p>
 * 当执行新的任务时它可以将其拆分分成更小的任务执行，并将小任务加到线程队列（此线程队列为双端队列）中，然后再从一个随机线程的队列的末尾中偷一个并把它放在自己的队列中。
 * <p>
 * 相对于一般的线程池实现,fork/join框架的优势体现在对其中包含的任务的处理方式上.在一般的线程池中,
 * 如果一个线程正在执行的任务由于某些原因无法继续运行,那么该线程会处于等待状态.而在fork/join框架实现中,
 * 如果某个子问题由于等待另外一个子问题的完成而无法继续运行.那么处理该子问题的线程会主动寻找其他尚未运行的子问题来执行.
 * 这种方式减少了线程的等待时间,提高了性能.
 * <p>
 * Fork的实现必须继承
 * RecursiveAction   无返回值
 * 或者
 * RecursiveTask  有返回值
 */
//实现数的累加操作
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private Long start;
    private Long end;

    private static final long THRESHOLD = 10000;//临界值

    //构造器
    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }


    /**
     * 需要自己去写fork/join的算法
     * 计算1到10000000000的累加
     * 先拆分成1到5000000000
     * 再拆分成1到2500000000
     * 再拆分。。。。
     * @return
     */
    @Override
    protected Long compute() {
        long length = end - start;
        //到了临界值就不再拆分
        if (length <= THRESHOLD) {
            long sum = 0;
            for (Long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        } else {
            //通过Fork/Join递归拆分成子任务
            long middle = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork();//拆分成子任务，同时压入线程队列
            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
            right.fork();//拆分成子任务，同时压入线程队列

            return left.join() + right.join();
        }
    }
}
