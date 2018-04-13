package basicconsoleapp.springdemo.concert

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.DeclareParents
import org.springframework.stereotype.Component

// 指定一个切面
@Aspect
// 声明为一个 bean
@Component
object EncoreableIntroducer {

    // 通过 @DeclareParents 注解
    // 将 Encoreable 接口引入到 Performance bean 中
    // value 属性指定了哪种类型的 bean 要引入该接口
    // 标记符后面的加号表示是 Performance 的所有子类型， 而不是 Performance 本身
    // defaultImpl 属性指定了为引入功能提供实现的类。 在这里，我们指定的是 DefaultEncoreable 提供实现。
    // @DeclareParents 注解所标注的静态属性指明了要引入的接口。 在这里， 我们所引入的是 Encoreable 接口。
    @DeclareParents(value = "basicconsoleapp.Performance+", defaultImpl = DefaultEncoreable::class)
    var encoreable: Encoreable? = null

}
