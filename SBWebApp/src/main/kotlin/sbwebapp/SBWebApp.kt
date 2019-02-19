package sbwebapp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer


// 需要继承 SpringBootServletInitializer
// 并重写 configure 方法，这样才能在 Tomcat 中部署
@SpringBootApplication
// 启动器启动时，扫描本目录以及子目录带有的 WebServlet/WebFilter 的注解
@ServletComponentScan
class SBWebApp : SpringBootServletInitializer() {
    override fun configure(builder: SpringApplicationBuilder?): SpringApplicationBuilder {
        return builder!!.sources(SBWebApp::class.java)
    }

    companion object {
        @JvmStatic
        fun main(vararg args: String) {
            SpringApplication.run(arrayOf(SBWebApp::class.java), args)
            // runApplication<SBWebApp>(*args)
        }
    }
}
