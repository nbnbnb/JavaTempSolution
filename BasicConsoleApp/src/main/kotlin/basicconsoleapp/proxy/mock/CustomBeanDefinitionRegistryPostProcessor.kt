package basicconsoleapp.proxy.mock


import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component


/**
 * Created by jinzhanga on 2018/8/3.
 */

@Component
class CustomBeanDefinitionRegistryPostProcessor : ApplicationContextAware, BeanDefinitionRegistryPostProcessor {

    private var applicationContext: ApplicationContext? = null

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        println("postProcessBeanFactory()")
    }

    override fun postProcessBeanDefinitionRegistry(registry: BeanDefinitionRegistry) {
        println("postProcessBeanDefinitionRegistry()")

        // 需要被代理的接口
        val annotationScanner = CustomClassPathBeanDefinitionScanner(registry)
        annotationScanner.setResourceLoader(applicationContext)

        annotationScanner.scan("basicconsoleapp")
        // annotationScanner.scan("") 不指定包，这种扫描方式很耗时
    }
}