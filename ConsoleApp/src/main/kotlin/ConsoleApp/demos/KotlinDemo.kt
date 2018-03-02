package ConsoleApp.demos

object KotlinDemo {
    fun test() {
        println("Hello World!")

        val lazyValue: String by lazy {
            println("computed!")
            "Hello"
        }
    }
}










