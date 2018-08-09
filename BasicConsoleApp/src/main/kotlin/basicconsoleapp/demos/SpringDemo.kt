package basicconsoleapp.demos

import basicconsoleapp.config.AppConfig
import basicconsoleapp.config.ConditionConfig
import basicconsoleapp.config.ELConfig
import basicconsoleapp.proxy.*
import basicconsoleapp.proxy.mock.MockAClass
import basicconsoleapp.proxy.mock.MockInterface
import basicconsoleapp.springdemo.concert.around.Arounder
import basicconsoleapp.springdemo.concert.aware.AwareService
import basicconsoleapp.springdemo.concert.basic.Performance
import basicconsoleapp.springdemo.concert.introducer.Encoreable
import basicconsoleapp.springdemo.concert.selector.IExecution
import basicconsoleapp.springdemo.ee.conditional.ListService
import basicconsoleapp.springdemo.ee.taskexecutor.AsyncTaskService
import org.apache.commons.io.IOUtils
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.core.env.get

object SpringDemo {

    fun basic() {
        val context = AnnotationConfigApplicationContext(AppConfig::class.java)
        val performance = context.getBean(Performance::class.java)
        performance.doIt()
        println("------")
        performance.perform()
        println("------")
        performance.showError()
    }

    fun selector() {
        val context = AnnotationConfigApplicationContext(AppConfig::class.java)
        val myExecution = context.getBean("myExecution", IExecution::class.java)
        myExecution.doIt()

        val sheExecution = context.getBean("sheExecution", IExecution::class.java)
        sheExecution.doIt()
    }

    fun introducer() {
        val context = AnnotationConfigApplicationContext(AppConfig::class.java)
        val performance = context.getBean(Performance::class.java)
        // 由于实现了“引入新功能”
        // 此处可以将 Performance 接口转型为 Encoreable 接口
        val encoreable = performance as Encoreable
        encoreable.performEncore()
    }

    fun around() {
        val context = AnnotationConfigApplicationContext(AppConfig::class.java)
        val arounder = context.getBean(Arounder::class.java)
        arounder.showInfo("JJZhang", 30)
    }

    fun el() {
        val context = AnnotationConfigApplicationContext(AppConfig::class.java)
        val elConfig = context.getBean(ELConfig::class.java)
        println(elConfig.normal)
        println(elConfig.osNameString)
        println(elConfig.randomNumber)
        println(elConfig.fromAnother)
        println(IOUtils.toString(elConfig.testFile.inputStream, "utf8"))
        println(IOUtils.toString(elConfig.testUrl.inputStream, "utf8"))
        println(elConfig.bookName)
        println(elConfig.env.getProperty("book.author"))
    }

    fun aware() {
        val context = AnnotationConfigApplicationContext(AppConfig::class.java)
        val awareService = context.getBean(AwareService::class.java)
        awareService.outputResult()
    }

    fun task() {
        val context = AnnotationConfigApplicationContext(AppConfig::class.java)
        val asyncTaskService = context.getBean(AsyncTaskService::class.java)

        for (i in 0..10) {
            asyncTaskService.executeAsyncTask(i)
            asyncTaskService.executeAsyncTaskPlus(i)
        }

        context.close()
    }

    fun schedule() {
        // 启动了 Spring 框架，就会启动 Scheduler
        val context = AnnotationConfigApplicationContext(AppConfig::class.java)

        // 如果调用了 close 方法，则计划任务将会关闭
        // context.close()
    }

    fun conditional() {
        val context = AnnotationConfigApplicationContext(ConditionConfig::class.java)
        val listService = context.getBean(ListService::class.java)
        println("${context.environment["os.name"]} 系统下的列表命令为 ${listService.showListCmd()}")
    }

    fun dynamicProxy() {
        val jdkFacadeImpl = JDKFacadeImpl()
        val jdkFacadeProxy = JDKFacadeProxy()
        val jdkFacade = jdkFacadeProxy.bind(jdkFacadeImpl) as JDKFacade
        jdkFacade.addBook()

        println()

        val cglibFacadeImpl = CGlibFacadeImpl()
        val cglibFacadeImplProxy = CGlibFacadeImplProxy()
        val cgiFacade = cglibFacadeImplProxy.getInstance(cglibFacadeImpl) as CGlibFacadeImpl
        cgiFacade.addBook()
    }

    fun dynamicInterfaceImpl() {
        val context = AnnotationConfigApplicationContext(AppConfig::class.java)
        val mock = context.getBean(MockInterface::class.java)
        val mockB = mock.dynamicMock(MockAClass())
        println(mockB)
    }

    /**
     * 启动 RMI 服务器
     */
    fun rmiServer(){
        // 命令行指定
        // -Djava.rmi.server.codebase=rmi://localhost/SpitterService
        System.setProperty("java.rmi.server.codebase", "rmi://localhost/SpitterService")
        AnnotationConfigApplicationContext(AppConfig::class.java)
        println("服务已发布 rmi://localhost/SpitterService")
    }
}
