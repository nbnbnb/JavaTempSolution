package concert;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EncoreableIntroducer {
    /*
    @DeclareParents注解由三部分组成：value属性指定了哪种类型的bean要引入该接口。 在本例中， 也就是所有实现Performance的类型。
        （ 标记符后面的加号表示是Performance的所有子类型， 而不是Performance本身。 ）

    defaultImpl属性指定了为引入功能提供实现的类。 在这里，我们指定的是DefaultEncoreable提供实现。

    @DeclareParents注解所标注的静态属性指明了要引入了接口。 在这里， 我们所引入的是Encoreable接口。
    */
    @DeclareParents(value = "concert.Performance+", defaultImpl = DefauleEncoreable.class)
    public static Encoreable encoreable;
}
