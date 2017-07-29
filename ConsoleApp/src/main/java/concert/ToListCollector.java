package concert;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
    @Override
    public Supplier<List<T>> supplier() {  // 创建集合操作的起始点
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() { // 累积遍历过的项目，原位修改累加器
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {  // 修改第一个累加器，将其与第二个累加器的内容合并
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {   // 恒等函数
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {  // 为收集器添加 IDENTITY_FINISH 和 CONCURRENT
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
