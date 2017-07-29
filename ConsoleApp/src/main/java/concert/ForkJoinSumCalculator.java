package concert;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends java.util.concurrent.RecursiveTask<Long> {

    public static final long THRESHOLD = 10_000;
    private final long[] numbers;
    private final int start;
    private final int end;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 1000000).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        Long bb = new ForkJoinPool().invoke(task);
        System.out.println(bb);
    }

    @Override
    protected Long compute() {

        int length = end - start;

        if (length <= THRESHOLD) {
            return computeSequentially();
        } else {
            ForkJoinSumCalculator leftTask =
                    new ForkJoinSumCalculator(numbers, start, start + length / 2);
            leftTask.fork();  // 将任务放进线程池中
            ForkJoinSumCalculator rightTask =
                    new ForkJoinSumCalculator(numbers, start + length / 2, end);

            /*
            对子任务调用fork方法可以把它排进ForkJoinPool。同时对左边和右边的子任务调用
            它似乎很自然，但这样做的效率要比直接对其中一个调用compute低。这样做你可以为
            其中一个子任务重用同一线程，从而避免在线程池中多分配一个任务造成的开销。*/
            Long rightResult = rightTask.compute();
            Long leftResult = leftTask.join();

            return leftResult + rightResult;
        }

    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

}
