import concert.ToListCollector;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by ZhangJin on 2017/7/8.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("----- start -----");
        Demo();
        System.out.println("-----  end  -----");
    }

    public static void Demo() {

        List<Dish> bb = null;

        List<Dish> dishes = bb.stream().collect(new ToListCollector<>());

        bb.stream().collect(ArrayList::new, List::add, List::addAll);
    }

    static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

}

class Dish {
}
