package basicconsoleapp.springdemo.concert.basic

// 在 BasicAspect 中
// 通过 execution(* basicconsoleapp.springdemo.concert.basic.Performance.*(..)) 切入点表达式进行注入
interface Performance {

    fun perform(): String

    fun doIt()

    fun showError()

}
