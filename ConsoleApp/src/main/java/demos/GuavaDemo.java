package demos;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

import javax.annotation.Nullable;


public class GuavaDemo {
    public static void optionalDemo() {

        Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {
            public String apply(Foo foo) {
                return foo.sortedBy;
            }
        });

    }
}

class Foo {
    @Nullable
    String sortedBy;
    int notSortedBy;
}